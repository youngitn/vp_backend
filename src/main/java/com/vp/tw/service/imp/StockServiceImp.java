package com.vp.tw.service.imp;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vp.tw.entity.t100.Inag;
import com.vp.tw.entity.t100.specification.builder.SpecificationsBuilder;
import com.vp.tw.repository.t100.InagDao;
import com.vp.tw.service.StockService;

/**
 * 
* @ClassName: StockServiceImp 
* @Description: TODO(這裡用一句話描述這個類的作用) 
* @author ytc
* @date 2020年7月29日 上午9:21:58 
*
 */
@Service
public class StockServiceImp implements StockService {
	@Autowired
	private InagDao inagDao;

	
	@Override
	public List<Inag> getStockInfo(String search) {
		SpecificationsBuilder builder = new SpecificationsBuilder();
		Pattern pattern = Pattern.compile("(and\\:|or\\:|where\\:)(\\w+?)(:|<|>|@|!=|<>)(\\w+?),",
				Pattern.UNICODE_CHARACTER_CLASS);

		Matcher matcher = pattern.matcher(search);

		while (matcher.find()) {

			builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
			System.out.println(
					matcher.group(1) + " " + matcher.group(2) + " " + matcher.group(3) + " " + matcher.group(4));
		}

		Specification<Inag> spec = builder.build();

		return inagDao.findAll(spec);

	}
	@Override
	public List<Inag> getStockInfoUseExample(Inag search) {
		
		

		return inagDao.findAll(Example.of(search));
	}

	
	public List<Inag> getStockInfoUseExample(String search) {
		// TODO Auto-generated method stub
		return null;
	}

}
