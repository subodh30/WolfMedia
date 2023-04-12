package edu.ncsu.dbms.wolfmedia.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class SongHistory {


    private int songId;
    private int month;
    private int year;
    private int playCount;

}
