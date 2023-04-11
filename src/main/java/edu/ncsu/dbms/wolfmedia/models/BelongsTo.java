package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class BelongsTo {
    private int artistTypeId;
    private int artistId;
}
