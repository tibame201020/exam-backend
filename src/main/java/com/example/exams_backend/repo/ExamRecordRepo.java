package com.example.exams_backend.repo;

import com.example.exams_backend.model.ExamRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRecordRepo extends JpaRepository<ExamRecord, Long> {
    List<ExamRecord> findByAnsQuizzesIsNull();
}
