package edu.ncsu.dbms.wolfmedia.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class PodcastHosts {

    private int hostId;
    private String firstName;
    private String lastName;
    private String contact;
    private String email;
    private String city;

}
