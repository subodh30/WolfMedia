package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class PodcastHistory {


    private int podcastId;
    private int month;
    private int year;
    private int subscribers;
    private int rating;

}
