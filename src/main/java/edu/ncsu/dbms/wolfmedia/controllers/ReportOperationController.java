package edu.ncsu.dbms.wolfmedia.controllers;

import edu.ncsu.dbms.wolfmedia.models.SongHistory;
import edu.ncsu.dbms.wolfmedia.services.ReportOperationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ReportOperationController {
    private final ReportOperationService reportOperationService;

    public ReportOperationController(ReportOperationService reportOperationService) {
        this.reportOperationService = reportOperationService;
    }

    @GetMapping("/playCountPeSongPerMonth")
    public List<SongHistory> getPlayCountPerSongPerMonth() throws Exception {
        return reportOperationService.getPlayCountPerSongPerMonth();
    }

    @GetMapping("/playCountPerAlbumPerMonth")
    public List<Map<String, Object>> getPlayCountPerAlbumPerMonth() throws Exception {
        return reportOperationService.getPlayCountPerAlbumPerMonth();
    }

    @GetMapping("/playCountPerArtistPerMonth")
    public List<Map<String, Object>> getPlayCountPerArtistPerMonth() throws Exception {
        return reportOperationService.getPlayCountPerArtistPerMonth();
    }

    @GetMapping("/calculateHostPayments")
    public Double calculateHostPayments(@RequestParam String startDate, @RequestParam String endDate) throws Exception {
        return reportOperationService.calculateHostPayments(startDate, endDate);
    }

    @GetMapping("/calculateArtistPayments")
    public Double calculateArtistPayments(@RequestParam String startDate, @RequestParam String endDate) throws Exception {
        return reportOperationService.calculateArtistPayments(startDate, endDate);
    }

    @GetMapping("/calculateRecordLabelPayments")
    public Double calculateRecordLabelPayments(@RequestParam String startDate, @RequestParam String endDate) throws Exception {
        return reportOperationService.calculateRecordLabelPayments(startDate, endDate);
    }

    @GetMapping("/calculateMonthlyRevenue")
    public List<Map<String, Object>> calculateMonthlyRevenue() throws Exception {
        return reportOperationService.calculateMonthlyRevenue();
    }

    @GetMapping("/calculateYearlyRevenue")
    public List<Map<String, Object>> calculateYearlyRevenue() throws Exception {
        return reportOperationService.calculateYearlyRevenue();
    }

    @GetMapping("/songsByArtist/{artistId}")
    public List<Map<String, Object>> getSongsByArtist(@PathVariable("artistId") int artistId) throws Exception {
        return reportOperationService.getSongsByArtist(artistId);
    }

    @GetMapping("/getSongsByAlbum/{albumId}")
    public List<Map<String, Object>> getSongsByAlbum(@PathVariable("albumId") int albumId) throws Exception {
        return reportOperationService.getSongsByAlbum(albumId);
    }

    @GetMapping("/getPodcastEpisodesByPodcast/{podcastId}")
    public List<Map<String, Object>> getPodcastEpisodesByPodcast(@PathVariable("podcastId") int podcastId) throws Exception {
        return reportOperationService.getPodcastEpisodesByPodcast(podcastId);
    }
}
