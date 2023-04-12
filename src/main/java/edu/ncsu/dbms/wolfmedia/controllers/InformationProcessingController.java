package edu.ncsu.dbms.wolfmedia.controllers;

import edu.ncsu.dbms.wolfmedia.models.*;
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

    //Songs
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


    //Albums
    @GetMapping("/albums")
    public List<Albums> getAlbums() {
        return informationProcessingService.getAlbums(-1);
    }

    @GetMapping("/albums/{id}")
    public Albums getAlbum(@PathVariable int id) {
        return informationProcessingService.getAlbums(id).get(0);
    }

    @PostMapping("/albums")
    public void addAlbum(@RequestBody Albums album) {
        informationProcessingService.addAlbum(album);
    }

    @PutMapping("/albums")
    public void updateAlbum(@RequestBody Albums album) {
        informationProcessingService.updateAlbum(album);
    }

    @DeleteMapping("/albums/{id}")
    public void deleteAlbum(@PathVariable int id) {
        informationProcessingService.deleteAlbum(id);
    }

    //Artists
    @GetMapping("/artists")
    public List<Artists> getArtists() {
        return informationProcessingService.getArtists(-1);
    }

    @GetMapping("/artists/{id}")
    public Artists getArtist(@PathVariable int id) {
        return informationProcessingService.getArtists(id).get(0);
    }

    @PostMapping("/artists")
    public void addArtist(@RequestBody Artists artist) {
        informationProcessingService.addArtist(artist);
    }

    @PutMapping("/artists")
    public void updateArtist(@RequestBody Artists artist) {
        informationProcessingService.updateArtist(artist);
    }

    @DeleteMapping("/artists/{id}")
    public void deleteArtist(@PathVariable int id) {
        informationProcessingService.deleteArtist(id);
    }

    //PodcastHost
    @GetMapping("/podcastHosts")
    public List<PodcastHosts> getPodcastHosts() {
        return informationProcessingService.getPodcastHosts(-1);
    }

    @GetMapping("/podcastHosts/{id}")
    public PodcastHosts getPodcastHost(@PathVariable int id) {
        return informationProcessingService.getPodcastHosts(id).get(0);
    }

    @PostMapping("/podcastHosts")
    public void addPodcastHost(@RequestBody PodcastHosts podcastHost) {
        informationProcessingService.addPodcastHost(podcastHost);
    }

    @PutMapping("/podcastHosts")
    public void updatePodcastHost(@RequestBody PodcastHosts podcastHost) {
        informationProcessingService.updatePodcastHost(podcastHost);
    }

    @DeleteMapping("/podcastHosts/{id}")
    public void deletePodcastHost(@PathVariable int id) {
        informationProcessingService.deletePodcastHost(id);
    }

    //Podcast
    @GetMapping("/podcasts")
    public List<Podcasts> getPodcasts() {
        return informationProcessingService.getPodcasts(-1);
    }

    @GetMapping("/podcasts/{id}")
    public Podcasts getPodcast(@PathVariable int id) {
        return informationProcessingService.getPodcasts(id).get(0);
    }

    @PostMapping("/podcasts")
    public void addPodcast(@RequestBody Podcasts podcast) {
        informationProcessingService.addPodcast(podcast);
    }

    @PutMapping("/podcasts")
    public void updatePodcast(@RequestBody Podcasts podcast) {
        informationProcessingService.updatePodcast(podcast);
    }

    @DeleteMapping("/podcasts/{id}")
    public void deletePodcast(@PathVariable int id) {
        informationProcessingService.deletePodcast(id);
    }

    //PodcastEpisode
    @GetMapping("/podcastEpisodes")
    public List<Episodes> getPodcastEpisodes() {
        return informationProcessingService.getPodcastEpisodes(-1);
    }

    @GetMapping("/podcastEpisodes/{id}")
    public Episodes getPodcastEpisode(@PathVariable int id) {
        return informationProcessingService.getPodcastEpisodes(id).get(0);
    }

    @PostMapping("/podcastEpisodes")
    public void addPodcastEpisode(@RequestBody Episodes podcastEpisode) {
        informationProcessingService.addPodcastEpisode(podcastEpisode);
    }

    @PutMapping("/podcastEpisodes")
    public void updatePodcastEpisode(@RequestBody Episodes podcastEpisode) {
        informationProcessingService.updatePodcastEpisode(podcastEpisode);
    }

    @DeleteMapping("/podcastEpisodes/{podcastId}/{number}")
    public void deletePodcastEpisode(@PathVariable int podcastId, @PathVariable int number) {
        informationProcessingService.deletePodcastEpisode(podcastId, number);
    }

}
