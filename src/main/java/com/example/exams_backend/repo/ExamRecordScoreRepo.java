package com.example.exams_backend.repo;

import com.example.exams_backend.model.ExamRecordScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRecordScoreRepo extends JpaRepository<ExamRecordScore, Long> {
}
