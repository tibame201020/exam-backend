package com.example.exams_backend.service;

import com.example.exams_backend.model.Exam;
import com.example.exams_backend.model.ExamModeParam;
import com.example.exams_backend.model.Quiz;

import java.util.List;

public interface ExamService {

    boolean saveExam(Exam exam);

    boolean findExamByName(String name);

    List<String> getExamList(String keyWord);

    boolean removeExam(String name);

    Exam getExamByName(String examName);

    Quiz[] getRandomQuizzes(ExamModeParam examModeParam);
}
