package org.imt.tournamentmaster.controller.Report;

import org.imt.tournamentmaster.model.report.Report;
import org.imt.tournamentmaster.service.report.ReportService;
import org.imt.tournamentmaster.dto.MatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public List<Report> getAll() {
        return reportService.getAll();
    }

    @PostMapping("/matches")
    public ResponseEntity<String> importMatches(@RequestBody List<MatchDto> matches) {


        String report = reportService.importMatches(matches);
        if(report == null) {
            return ResponseEntity.badRequest().body("Error while importing matches");
        }
        return ResponseEntity.ok(report);
    }
}
