package edu.ncsu.dbms.wolfmedia.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class PodcastHistory {

    private int podcastId;
    private int month;
    private int year;
    private int subscribers;
    private double rating;

}
