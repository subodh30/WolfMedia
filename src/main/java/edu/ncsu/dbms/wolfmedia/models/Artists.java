package edu.ncsu.dbms.wolfmedia.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class Artists {
    private int artistId;
    private String name;
    private String status;
    private String country;
    private String primaryGenre;
    private int monthlyListeners;
    private int recordId;

}
