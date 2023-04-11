package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class Albums {
    private int albumId;
    private String name;
    private int releaseYear;
    private String edition;
}
