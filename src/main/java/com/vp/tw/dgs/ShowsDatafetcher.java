package com.vp.tw.dgs;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import graphql.schema.DataFetchingEnvironment;
import lombok.extern.log4j.Log4j2;

@Log4j2
@DgsComponent
public class ShowsDatafetcher {

	private final List<Show> shows = List.of(new Show("Stranger Things", 2016), new Show("Ozark", 2017),
			new Show("The Crown", 2016), new Show("Dead to Me", 2019), new Show("Orange is the New Black", 2013));

//	@DgsData(parentType = "Query", field = "shows")
//	public List<Show> shows(@InputArgument("title") String title, @InputArgument("releaseYear") Integer releaseYear) {
//		if (title == null) {
//			return shows;
//		}
//
//		return shows.stream().filter(s -> s.getTitle().contains(title)).collect(Collectors.toList());
//	}

	@DgsData(parentType = "Query", field = "shows")
	public List<Show> getShows(DataFetchingEnvironment dataFetchingEnvironment) {
		log.info("actors----->>>>"+dataFetchingEnvironment.getArgument("title"));
		String  title 		= dataFetchingEnvironment.getArgument("title");
		Integer releaseYear = dataFetchingEnvironment.getArgument("releaseYear");
//		if (dataFetchingEnvironment.getArgument("releaseYear") == null) {
//			releaseYear = dataFetchingEnvironment.getArgument("releaseYear");
//		}
		
		if (title == null && releaseYear == null) {
			return shows;
		}
		
		
		
		return shows.stream().filter(
				s -> (s.getTitle().contains(title)&& s.getReleaseYear().equals(releaseYear))
				).collect(Collectors.toList());
	}

	@DgsData(parentType = "Query", field = "showxs")
	public List<Show> showxs() {
		ShowsDatafetcher.log.info("actors----->>>>");

		// actorsService.forShow(show.getTitle());
		// ShowsDatafetcher.log.info("actors----->>>>"+show.getTitle());

		return shows;

	}
}
