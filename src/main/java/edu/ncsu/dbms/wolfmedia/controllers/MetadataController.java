package edu.ncsu.dbms.wolfmedia.controllers;

import edu.ncsu.dbms.wolfmedia.models.Episodes;
import edu.ncsu.dbms.wolfmedia.models.Songs;
import edu.ncsu.dbms.wolfmedia.services.MetadataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MetadataController {

    private final MetadataService metadataService;

    public MetadataController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @GetMapping("/addPlayCountSong")
    public Boolean p(@RequestParam Integer songId, @RequestParam Integer playCount) throws Exception {
        return metadataService.addPlayCountSong(songId, playCount);
    }

    @GetMapping("/addMonthlyListeners")
    public Boolean addMonthlyListeners(@RequestParam Integer artistId, @RequestParam Integer monthlyListeners)
            throws Exception {
        return metadataService.addMonthlyListeners(artistId, monthlyListeners);
    }

    @GetMapping("/addTotalSubscribers")
    public Boolean addTotalSubscribers(@RequestParam Integer podcastId, @RequestParam Integer totalSubscribers)
            throws Exception {
        return metadataService.addTotalSubscribers(podcastId, totalSubscribers);
    }

    @GetMapping("/addPodcastRatings")
    public Boolean addPodcastRatings(@RequestParam Integer podcastId, @RequestParam Double rating) throws Exception {
        return metadataService.addPodcastRatings(podcastId, rating);
    }

    @GetMapping("/addPodcastEpisodeListenerCount")
    public Boolean addPodcastEpisodeListenerCount(@RequestParam Integer podcastId, @RequestParam Integer number,
            Integer ListeningCount) throws Exception {
        return metadataService.addPodcastEpisodeListenerCount(podcastId, number, ListeningCount);
    }

    @GetMapping("/updatePlayCountSong")
    public Boolean updatePlayCountSong(@RequestParam Integer songId, @RequestParam Integer playCount) throws Exception {
        return metadataService.updatePlayCountSong(songId, playCount);
    }

    @GetMapping("/updateMonthlyListeners")
    public Boolean updateMonthlyListeners(@RequestParam Integer artistId, @RequestParam Integer monthlyListeners)
            throws Exception {
        return metadataService.updateMonthlyListeners(artistId, monthlyListeners);
    }

    @GetMapping("/updateTotalSubscribers")
    public Boolean updateTotalSubscribers(@RequestParam Integer podcastId, @RequestParam Integer totalSubscribers)
            throws Exception {
        return metadataService.updateTotalSubscribers(podcastId, totalSubscribers);
    }

    @GetMapping("/updatePodcastRatings")
    public Boolean updatePodcastRatings(@RequestParam Integer podcastId, @RequestParam Double rating)
            throws Exception {
        return metadataService.updatePodcastRatings(podcastId, rating);
    }

    @GetMapping("/updatePodcastEpisodeListenerCount")
    public Boolean updatePodcastEpisodeListenerCount(@RequestParam Integer podcastId, @RequestParam Integer number,
            @RequestParam Integer ListeningCount) throws Exception {
        return metadataService.updatePodcastEpisodeListenerCount(podcastId, number, ListeningCount);
    }

    @GetMapping("/findSongsByArtist")
    public List<Map<String, Object>> findSongsByArtist(@RequestParam Integer artistId) throws Exception {
        return metadataService.findSongsByArtist(artistId);
    }

    @GetMapping("/findSongsByAlbum")
    public List<Songs> findSongsByAlbum(@RequestParam Integer albumId) throws Exception {
        return metadataService.findSongsByAlbum(albumId);
    }

    @GetMapping("/findPodcastEpisodes")
    public List<Episodes> findPodcastEpisodes(@RequestParam Integer podcastId) throws Exception {
        return metadataService.findPodcastEpisodes(podcastId);
    }

}
