package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class PodcastHosts {

    private int hostId;
    private String firstName;
    private String lastName;
    private String contact;
    private String email;
    private String city;

}
