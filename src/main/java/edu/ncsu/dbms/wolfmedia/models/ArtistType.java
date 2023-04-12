package edu.ncsu.dbms.wolfmedia.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class ArtistType {

    private int artistTypeId;
    private String type;

}
