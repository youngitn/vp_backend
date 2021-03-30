package com.vp.tw.dgs;

import lombok.Data;

@Data
public class Show {
    private final String title;
    private final Integer releaseYear   ;

    public Show(String title, Integer releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

   
}
