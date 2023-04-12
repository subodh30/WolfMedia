package edu.ncsu.dbms.wolfmedia.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonSerialize
@Data
@AllArgsConstructor
public class Songs {

    private String songId;
    private Double royaltyRate;
    private String title;
    private String royaltyStatus;
    private Integer playCount;
    private String country;
    private String language;
    private Double duration;
    private Integer primaryArtist;
    private Integer albumId;
}
