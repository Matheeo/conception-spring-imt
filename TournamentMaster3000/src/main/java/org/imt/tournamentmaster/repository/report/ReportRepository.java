package org.imt.tournamentmaster.repository.report;

import org.imt.tournamentmaster.model.report.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository  extends CrudRepository<Report, Long> {
}
