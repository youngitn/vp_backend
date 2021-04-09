package com.vp.tw.dgs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.vp.tw.entity.t100.Inag;
import com.vp.tw.entity.t100.Inayl;
import com.vp.tw.service.StockService;

import lombok.extern.log4j.Log4j2;

/**
 * 
* @ClassName: InagsDatafetcher 
* @Description: 測試Dgs框架整合現有entity的實作範例 
* @author ytc
* @date 2021年3月31日 下午3:16:18 
*
 */
@Log4j2
@DgsComponent//定義這個類別為Dgs專用處理元件
public class InagsDatafetcher {
	
	@Autowired
	StockService inagService;
	
	/**
	 * 這是schema.graphqls中 Query的定義
	type Query {
		///在@DgsComponent中要這樣寫->@DgsData(parentType = "Query", field = "shows")
	    shows(title: String ,releaseYear: Int): [Show]
	    ///在@DgsComponent中要這樣寫->@DgsData(parentType = "Query", field = "showxs")
	    showxs:[Show]
	    ///在@DgsComponent中要這樣寫->@DgsData(parentType = "Query", field = "inags")
	    inags(inag001:String): [Inag]
	    ///在@DgsComponent中要這樣寫->@DgsData(parentType = "Query", field = "getInaylTest")
	    getInaylTest(inayl001:String):[InaylTest]
	}
	**/
	//parentType=執行該方法查詢時 你的父階層 Query表示root階層 就是第二層.
	//可見schema.graphqls的定義type Query中定義所有查詢的起手式,包括帶入參數的定義
	//field=你在schema.graphqls >> type Query中的名稱,可以想成執行方法的mapping ID
	@DgsData(parentType = "Query", field = "getInag")
	public List<Inag> getInag(DgsDataFetchingEnvironment dataFetchingEnvironment) {
		log.info("actors----->>>>"+dataFetchingEnvironment.getArgument("inag001"));
		String  title 		= dataFetchingEnvironment.getArgument("inag001");
		Inag inag = new Inag();
		inag.setInag001(title);
		List<Inag> list = inagService.getStockInfoUseExample(inag);

		
		if (title == null ) {
			return null;
		}
		
		
		
		return list;
	}
	@DgsData(parentType = "Inag", field = "inaylTest")
	public Inayl getInaylTest(DgsDataFetchingEnvironment dfe) {
		
		Inag inag = dfe.getSource();

		log.info("明細查詢inaylTest----->>>>"+inag.getInag004());
		String  inayl001 		= inag.getInag004();
		

		
		Inayl Inayll = new Inayl();
		Inayll.setInayl001(inayl001);
		Inayll.setInayl003(inag.getInayl().get(0).getInayl003());
//		ArrayList<Inayl> lll = new ArrayList<Inayl>();
//		lll.add(Inayll);
		return Inayll;
	}
	
	@DgsData(parentType = "Inag", field = "getInagDataLoader")
	public CompletableFuture<List<Inag>> getInagDataLoader(DgsDataFetchingEnvironment dfe) {
		
		
		DataLoader<String, List<Inag>> inagDataLoader =dfe.getDataLoader("Inags");
		String  title 		= dfe.getArgument("inag001");
		log.info("actors----->>>>"+title);

		
		if (title == null ) {
			return null;
		}
		
		
//		String lsssist = new ArrayList();
//		lsssist.add(title);
		return inagDataLoader.load(title);
	}
}
