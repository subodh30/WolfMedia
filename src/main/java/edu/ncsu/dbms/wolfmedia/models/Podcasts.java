package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class Podcasts {

    private int podcastId;
    private String name;
    private String country;
    private String language;
    private int rating;
    private int episodeCount;
    private int flatFee;
    private int totalSubscribers;

}
