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
    public Boolean addSong(@RequestBody Songs song) throws Exception {
        return informationProcessingService.addSong(song);
    }

    @PutMapping("/songs")
    public Boolean updateSong(@RequestBody Songs song) throws Exception {
        return informationProcessingService.updateSong(song);
    }

    @DeleteMapping("/songs/{id}")
    public Boolean deleteSong(@PathVariable int id) throws Exception {
        return informationProcessingService.deleteSong(id);
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
    public Boolean addAlbum(@RequestBody Albums album)  throws Exception{
        return informationProcessingService.addAlbum(album);
    }

    @PutMapping("/albums")
    public Boolean updateAlbum(@RequestBody Albums album) throws Exception {
        return informationProcessingService.updateAlbum(album);
    }

    @DeleteMapping("/albums/{id}")
    public Boolean deleteAlbum(@PathVariable int id) throws Exception {
        return informationProcessingService.deleteAlbum(id);
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
    public Boolean addArtist(@RequestBody Artists artist)  throws Exception{
        return informationProcessingService.addArtist(artist);
    }

    @PutMapping("/artists")
    public Boolean updateArtist(@RequestBody Artists artist)  throws Exception{
        return informationProcessingService.updateArtist(artist);
    }

    @DeleteMapping("/artists/{id}")
    public Boolean deleteArtist(@PathVariable int id) throws Exception {
        return informationProcessingService.deleteArtist(id);
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
    public Boolean addPodcastHost(@RequestBody PodcastHosts podcastHost) throws Exception {
        return informationProcessingService.addPodcastHost(podcastHost);
    }

    @PutMapping("/podcastHosts")
    public Boolean updatePodcastHost(@RequestBody PodcastHosts podcastHost) throws Exception {
        return informationProcessingService.updatePodcastHost(podcastHost);
    }

    @DeleteMapping("/podcastHosts/{id}")
    public Boolean deletePodcastHost(@PathVariable int id)  throws Exception{
        return informationProcessingService.deletePodcastHost(id);
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
    public Boolean addPodcast(@RequestBody Podcasts podcast) throws Exception {
        return informationProcessingService.addPodcast(podcast);
    }

    @PutMapping("/podcasts")
    public Boolean updatePodcast(@RequestBody Podcasts podcast) throws Exception {
        return informationProcessingService.updatePodcast(podcast);
    }

    @DeleteMapping("/podcasts/{id}")
    public Boolean deletePodcast(@PathVariable int id)  throws Exception{
        return informationProcessingService.deletePodcast(id);
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
    public Boolean addPodcastEpisode(@RequestBody Episodes podcastEpisode) throws Exception {
        return informationProcessingService.addPodcastEpisode(podcastEpisode);
    }

    @PutMapping("/podcastEpisodes")
    public Boolean updatePodcastEpisode(@RequestBody Episodes podcastEpisode) throws Exception {
        return informationProcessingService.updatePodcastEpisode(podcastEpisode);
    }

    @DeleteMapping("/podcastEpisodes/{podcastId}/{number}")
    public Boolean deletePodcastEpisode(@PathVariable int podcastId, @PathVariable int number)  throws Exception{
        return informationProcessingService.deletePodcastEpisode(podcastId, number);
    }

    // new

    // ArtistToAlbum
    @GetMapping("/artists/{artistId}/albums/{albumId}")
    public Boolean assignArtistToAlbum(@PathVariable int artistId, @PathVariable int albumId)  throws Exception{
        return informationProcessingService.assignArtistToAlbum(artistId, albumId);
    }

    // SongToAlbum
    @GetMapping("/songs/{songId}/albums/{albumId}")
    public Boolean assignSongToAlbum(@PathVariable int songId, @PathVariable int albumId) throws Exception {
        return informationProcessingService.assignSongToAlbum(songId, albumId);
    }

    // ArtistToRecordLabel
    @GetMapping("/artists/{artistId}/recordLabels/{recordLabelId}")
    public Boolean assignArtistToRecordLabel(@PathVariable int artistId, @PathVariable int recordLabelId)  throws Exception{
        return informationProcessingService.assignArtistToRecordLabel(artistId, recordLabelId);
    }

    // EpisodeToPodcast
    @GetMapping("/episodes/{episodeId}/podcasts/{podcastId}")
    public Boolean assignEpisodeToPodcast(@PathVariable int episodeId, @PathVariable int podcastId) throws Exception {
        return informationProcessingService.assignEpisodeToPodcast(episodeId, podcastId);
    }

    // PodcastHostToPodcast
    @GetMapping("/podcasts/{podcastId}/hosts/{hostId}")
    public Boolean assignPodcastHostToPodcast(@PathVariable int podcastId, @PathVariable int hostId) throws Exception {
        return informationProcessingService.assignPodcastHostToPodcast(podcastId, hostId);
    }

}
