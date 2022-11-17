package com.example.exams_backend.controller;


import com.example.exams_backend.model.*;
import com.example.exams_backend.service.ExamModeService;
import com.example.exams_backend.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExamModeController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamModeService examModeService;

    @RequestMapping("/getExamModeQuizzes")
    public ExamRecord getExam(@RequestBody ExamModeParam examModeParam) {
        Quiz[] quizzes = examService.getRandomQuizzes(examModeParam);
        return examModeService.generateNewRecord(quizzes, examModeParam);
    }

    @RequestMapping("/commitAnsToRecord")
    public ExamRecord commitAnsToRecord(@RequestBody ExamRecord examRecord) {
        return examModeService.saveExamRecord(examRecord);
    }

    @RequestMapping("/getScoreById")
    public ExamRecordScore getScoreById(@RequestBody long id) {
        return examModeService.getExamRecordScoreById(id);
    }

    @RequestMapping("/getScoreByKeyword")
    public List<ExamRecordScore> getScoreByKeyword(@RequestBody String keyword) {
        return examModeService.getScoreList(keyword);
    }
    @RequestMapping("/deleteRecordScore")
    public boolean deleteRecordScore(@RequestBody long id) {
        return examModeService.deleteRecordScore(id);
    }

    @RequestMapping("/getExamRecordById")
    public ExamRecord getExamRecordById(@RequestBody long id) {
        return examModeService.findById(id);
    }
}
