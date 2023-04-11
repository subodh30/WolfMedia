package edu.ncsu.dbms.wolfmedia.controllers;

import edu.ncsu.dbms.wolfmedia.models.Songs;
import org.springframework.web.bind.annotation.*;
import edu.ncsu.dbms.wolfmedia.services.InformationProcessingService;

import java.util.List;

@RestController
public class InformationProcessingController {

    private final InformationProcessingService informationProcessingService;

    public InformationProcessingController(InformationProcessingService informationProcessingService) {
        this.informationProcessingService = informationProcessingService;
    }

    @GetMapping("/tables")
    public List<String> getTables() {
        return informationProcessingService.getTables();
    }

    @GetMapping("/{table}/attributes")
    public List<String> getTableAttributes(@PathVariable String table) {
        return informationProcessingService.getTableAttributes(table);
    }

    @GetMapping("/songs")
    public List<Songs> getSongs() {
        return informationProcessingService.getSongs(-1);
    }

    @GetMapping("/songs/{id}")
    public Songs getSong(@PathVariable int id) {
        return informationProcessingService.getSongs(id).get(0);
    }

    @PostMapping("/songs")
    public void addSong(@RequestBody Songs song) {
        informationProcessingService.addSong(song);
    }

    @PutMapping("/songs")
    public void updateSong(@RequestBody Songs song) {
        informationProcessingService.updateSong(song);
    }

    @DeleteMapping("/songs/{id}")
    public void deleteSong(@PathVariable int id) {
        informationProcessingService.deleteSong(id);
    }


}
