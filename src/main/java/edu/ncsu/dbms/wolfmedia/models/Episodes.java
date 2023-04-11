package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class Episodes {

    private int podcastId;
    private int number;
    private String title;
    private int duration;
    private String releaseDate;
    private int ListeningCount;
    private int AdvertisementCount;

}
