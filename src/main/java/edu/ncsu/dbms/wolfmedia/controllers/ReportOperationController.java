package edu.ncsu.dbms.wolfmedia.controllers;

import edu.ncsu.dbms.wolfmedia.models.SongHistory;
import edu.ncsu.dbms.wolfmedia.services.ReportOperationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ReportOperationController {
    private final ReportOperationService reportOperationService;

    public ReportOperationController(ReportOperationService reportOperationService) {
        this.reportOperationService = reportOperationService;
    }

    @GetMapping("/playCountPersongPerMonth")
    public List<SongHistory> getPlayCountPerSongPerMonth() {
        return reportOperationService.getPlayCountPerSongPerMonth();
    }

    @GetMapping("/playCountPerAlbumPerMonth")
    public List<Map<String, Object>> getPlayCountPerAlbumPerMonth() {
        return reportOperationService.getPlayCountPerAlbumPerMonth();
    }

    @GetMapping("/playCountPerArtistPerMonth")
    public List<Map<String, Object>> getPlayCountPerArtistPerMonth() {
        return reportOperationService.getPlayCountPerArtistPerMonth();
    }

    @GetMapping("/calculateHostPayments")
    public Double calculateHostPayments() {
        return reportOperationService.calculateHostPayments("2023-01-01", "2023-03-01");
    }
}
