package edu.ncsu.dbms.wolfmedia.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class Episodes {

    private int podcastId;
    private int number;
    private String title;
    private double duration;
    private String releaseDate;
    private int ListeningCount;
    private int AdvertisementCount;

}
