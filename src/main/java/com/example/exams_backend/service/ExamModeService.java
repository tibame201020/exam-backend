package com.example.exams_backend.service;

import com.example.exams_backend.model.ExamModeParam;
import com.example.exams_backend.model.ExamRecord;
import com.example.exams_backend.model.ExamRecordScore;
import com.example.exams_backend.model.Quiz;

import java.util.List;

public interface ExamModeService {
    ExamRecord generateNewRecord(Quiz[] quizzes, ExamModeParam examModeParam);

    ExamRecord saveExamRecord(ExamRecord examRecord);

    ExamRecordScore getExamRecordScoreById(long id);

    List<ExamRecordScore> getScoreList(String keyword);

    boolean deleteRecordScore(long id);

    ExamRecord findById(long id);
}
