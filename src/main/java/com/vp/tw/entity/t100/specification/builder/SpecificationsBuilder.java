package com.vp.tw.entity.t100.specification.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.vp.tw.entity.t100.Inag;
import com.vp.tw.entity.t100.specification.InagSpecification;
import com.vp.tw.entity.t100.specification.SearchCriteria;

public class SpecificationsBuilder {
    
    private final List<SearchCriteria> params;
    private String isOr = "";
    public SpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
 
    public SpecificationsBuilder with(String isOr,String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        this.isOr = isOr;
        return this;
    }
 
    public Specification<Inag> build() {
        if (params.size() == 0) {
            return null;
        }
 
        List<Specification> specs = params.stream()
          .map(InagSpecification::new)
          .collect(Collectors.toList());
        
        Specification result = specs.get(0);
 
        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
              .isOrPredicate(this.isOr)
                ? Specification.where(result)
                  .or(specs.get(i))
                : Specification.where(result)
                  .and(specs.get(i));
        }       
        return result;
    }
}
