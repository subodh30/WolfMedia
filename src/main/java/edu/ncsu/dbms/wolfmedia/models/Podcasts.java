package edu.ncsu.dbms.wolfmedia.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class Podcasts {

    private int podcastId;
    private String name;
    private String country;
    private String language;
    private int rating;
    private int episodeCount;
    private double flatFee;
    private int totalSubscribers;

}
