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
    public List<String> getTables() throws Exception {
        return informationProcessingService.getTables();
    }

    @GetMapping("/{table}/attributes")
    public List<String> getTableAttributes(@PathVariable String table) throws Exception {
        return informationProcessingService.getTableAttributes(table);
    }

    // Songs
    @GetMapping("/songs")
    public List<Songs> getSongs()  throws Exception{
        return informationProcessingService.getSongs(-1);
    }

    @GetMapping("/songs/{id}")
    public Songs getSong(@PathVariable int id) throws Exception {
        return informationProcessingService.getSongs(id).get(0);
    }

    @PostMapping("/songs")
    public void addSong(@RequestBody Songs song) throws Exception {
        informationProcessingService.addSong(song);
    }

    @PutMapping("/songs")
    public void updateSong(@RequestBody Songs song) throws Exception {
        informationProcessingService.updateSong(song);
    }

    @DeleteMapping("/songs/{id}")
    public void deleteSong(@PathVariable int id) throws Exception {
        informationProcessingService.deleteSong(id);
    }

    // Albums
    @GetMapping("/albums")
    public List<Albums> getAlbums()  throws Exception{
        return informationProcessingService.getAlbums(-1);
    }

    @GetMapping("/albums/{id}")
    public Albums getAlbum(@PathVariable int id) throws Exception {
        return informationProcessingService.getAlbums(id).get(0);
    }

    @PostMapping("/albums")
    public void addAlbum(@RequestBody Albums album)  throws Exception{
        informationProcessingService.addAlbum(album);
    }

    @PutMapping("/albums")
    public void updateAlbum(@RequestBody Albums album) throws Exception {
        informationProcessingService.updateAlbum(album);
    }

    @DeleteMapping("/albums/{id}")
    public void deleteAlbum(@PathVariable int id) throws Exception {
        informationProcessingService.deleteAlbum(id);
    }

    // Artists
    @GetMapping("/artists")
    public List<Artists> getArtists() throws Exception {
        return informationProcessingService.getArtists(-1);
    }

    @GetMapping("/artists/{id}")
    public Artists getArtist(@PathVariable int id) throws Exception {
        return informationProcessingService.getArtists(id).get(0);
    }

    @PostMapping("/artists")
    public void addArtist(@RequestBody Artists artist)  throws Exception{
        informationProcessingService.addArtist(artist);
    }

    @PutMapping("/artists")
    public void updateArtist(@RequestBody Artists artist)  throws Exception{
        informationProcessingService.updateArtist(artist);
    }

    @DeleteMapping("/artists/{id}")
    public void deleteArtist(@PathVariable int id) throws Exception {
        informationProcessingService.deleteArtist(id);
    }

    // PodcastHost
    @GetMapping("/podcastHosts")
    public List<PodcastHosts> getPodcastHosts()  throws Exception{
        return informationProcessingService.getPodcastHosts(-1);
    }

    @GetMapping("/podcastHosts/{id}")
    public PodcastHosts getPodcastHost(@PathVariable int id) throws Exception {
        return informationProcessingService.getPodcastHosts(id).get(0);
    }

    @PostMapping("/podcastHosts")
    public void addPodcastHost(@RequestBody PodcastHosts podcastHost) throws Exception {
        informationProcessingService.addPodcastHost(podcastHost);
    }

    @PutMapping("/podcastHosts")
    public void updatePodcastHost(@RequestBody PodcastHosts podcastHost) throws Exception {
        informationProcessingService.updatePodcastHost(podcastHost);
    }

    @DeleteMapping("/podcastHosts/{id}")
    public void deletePodcastHost(@PathVariable int id)  throws Exception{
        informationProcessingService.deletePodcastHost(id);
    }

    // Podcast
    @GetMapping("/podcasts")
    public List<Podcasts> getPodcasts() throws Exception {
        return informationProcessingService.getPodcasts(-1);
    }

    @GetMapping("/podcasts/{id}")
    public Podcasts getPodcast(@PathVariable int id) throws Exception {
        return informationProcessingService.getPodcasts(id).get(0);
    }

    @PostMapping("/podcasts")
    public void addPodcast(@RequestBody Podcasts podcast) throws Exception {
        informationProcessingService.addPodcast(podcast);
    }

    @PutMapping("/podcasts")
    public void updatePodcast(@RequestBody Podcasts podcast) throws Exception {
        informationProcessingService.updatePodcast(podcast);
    }

    @DeleteMapping("/podcasts/{id}")
    public void deletePodcast(@PathVariable int id)  throws Exception{
        informationProcessingService.deletePodcast(id);
    }

    // PodcastEpisode
    @GetMapping("/podcastEpisodes")
    public List<Episodes> getPodcastEpisodes()  throws Exception{
        return informationProcessingService.getPodcastEpisodes(-1);
    }

    @GetMapping("/podcastEpisodes/{id}")
    public List<Episodes> getPodcastEpisode(@PathVariable int id) throws Exception {
        return informationProcessingService.getPodcastEpisodes(id);
    }

    @PostMapping("/podcastEpisodes")
    public void addPodcastEpisode(@RequestBody Episodes podcastEpisode) throws Exception {
        informationProcessingService.addPodcastEpisode(podcastEpisode);
    }

    @PutMapping("/podcastEpisodes")
    public void updatePodcastEpisode(@RequestBody Episodes podcastEpisode) throws Exception {
        informationProcessingService.updatePodcastEpisode(podcastEpisode);
    }

    @DeleteMapping("/podcastEpisodes/{podcastId}/{number}")
    public void deletePodcastEpisode(@PathVariable int podcastId, @PathVariable int number)  throws Exception{
        informationProcessingService.deletePodcastEpisode(podcastId, number);
    }

    // new

    // ArtistToAlbum
    @PostMapping("/artists/{artistId}/albums/{albumId}")
    public void assignArtistToAlbum(@PathVariable int artistId, @PathVariable int albumId)  throws Exception{
        informationProcessingService.assignArtistToAlbum(artistId, albumId);
    }

    // SongToAlbum
    @PutMapping("/songs/{songId}/albums/{albumId}")
    public void assignSongToAlbum(@PathVariable int songId, @PathVariable int albumId) throws Exception {
        informationProcessingService.assignSongToAlbum(songId, albumId);
    }

    // ArtistToRecordLabel
    @PutMapping("/artists/{artistId}/recordLabels/{recordLabelId}")
    public void assignArtistToRecordLabel(@PathVariable int artistId, @PathVariable int recordLabelId)  throws Exception{
        informationProcessingService.assignArtistToRecordLabel(artistId, recordLabelId);
    }

    // EpisodeToPodcast
    @PutMapping("/episodes/{episodeId}/podcasts/{podcastId}")
    public void assignEpisodeToPodcast(@PathVariable int episodeId, @PathVariable int podcastId) throws Exception {
        informationProcessingService.assignEpisodeToPodcast(episodeId, podcastId);
    }

    // PodcastHostToPodcast
    @PostMapping("/podcasts/{podcastId}/hosts/{hostId}")
    public void assignPodcastHostToPodcast(@PathVariable int podcastId, @PathVariable int hostId) throws Exception {
        informationProcessingService.assignPodcastHostToPodcast(podcastId, hostId);
    }

}
