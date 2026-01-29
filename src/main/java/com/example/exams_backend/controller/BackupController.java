package com.example.exams_backend.controller;

import com.example.exams_backend.model.BackupData;
import com.example.exams_backend.repo.ExamRecordRepo;
import com.example.exams_backend.repo.ExamRecordScoreRepo;
import com.example.exams_backend.repo.ExamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backup")
public class BackupController {

    @Autowired
    private ExamRepo examRepo;

    @Autowired
    private ExamRecordRepo examRecordRepo;

    @Autowired
    private ExamRecordScoreRepo examRecordScoreRepo;

    @GetMapping("/export")
    public BackupData exportAll(@RequestParam(defaultValue = "true") boolean includeHistory) {
        BackupData backup = new BackupData();
        backup.setExams(examRepo.findAll());
        if (includeHistory) {
            backup.setRecords(examRecordRepo.findAll());
            backup.setScores(examRecordScoreRepo.findAll());
        }
        return backup;
    }

    @PostMapping("/import")
    @Transactional
    public boolean importData(@RequestBody BackupData data) {
        try {
            if (data == null)
                return false;

            // Import Exams
            if (data.getExams() != null) {
                examRepo.deleteAll();
                examRepo.saveAll(data.getExams());
            }

            // Import History (Records & Scores)
            // If data contains records/scores, we overwrite existing ones.
            // If data DOES NOT contain records/scores (e.g. export without history),
            // should we clear existing history?
            // Based on "Import overwrites existing data", yes, likely we want to sync
            // state.
            // But if the backup intentionally lacks history, does that mean "delete
            // history"?
            // Usually "Restore Backup" implies "Make system look like this backup".
            // So if backup has no history, system should have no history.

            // However, to be safe and flexible, if the list is null, we might skip it?
            // But an empty list is different from null.
            // JSON serialization usually omits null fields or sends null.
            // Let's assume if the field is present in JSON (even empty), we process it.
            // If it's null, we might ignore it (allow partial restore? e.g. import exams
            // only?).
            // For now, let's strictly follow "Overwrite" for whatever is provided.

            if (data.getRecords() != null) {
                examRecordRepo.deleteAll();
                examRecordRepo.saveAll(data.getRecords());
            }

            if (data.getScores() != null) {
                examRecordScoreRepo.deleteAll();
                examRecordScoreRepo.saveAll(data.getScores());
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
