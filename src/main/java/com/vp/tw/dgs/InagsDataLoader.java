package com.vp.tw.dgs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.dataloader.BatchLoader;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.graphql.dgs.DgsDataLoader;
import com.vp.tw.entity.t100.Inag;
import com.vp.tw.service.StockService;

@DgsDataLoader(name = "Inags")
public class InagsDataLoader  implements BatchLoader<String, Inag> {

	@Autowired
    StockService service;
	
	@Override
	public CompletionStage<List<Inag>> load(List<String> keys) {
		System.out.println("------------>IN load" + keys );
		//service.getStockInfo(keys.get(0));
		// TODO Auto-generated method stub
		return CompletableFuture.supplyAsync(() -> buildSample());
	}
	
	private List<Inag> buildSample(){
		
		List<Inag> al = new ArrayList<Inag>();
		Inag I = new Inag();
		I.setInag003("777777");
		I.setInag001("888888");
		al.add(I);
		return al;
		
	}

}
