package com.example.exams_backend.controller;

import com.example.exams_backend.model.Exam;
import com.example.exams_backend.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private ExamService examService;

    @RequestMapping("/addExam")
    public boolean addQuizzes(@RequestBody Exam exam) {
        return examService.saveExam(exam);
    }

    @RequestMapping("/checkExamNm")
    public boolean checkExamNm(@RequestBody String name) {
        return examService.findExamByName(name);
    }

    @RequestMapping("/getExamList")
    public List<String> getExamList() {
        return examService.getExamList("");
    }

    @RequestMapping("/getExamListByKeyWord")
    public List<String> getExamList(@RequestBody String keyWord) {
        return examService.getExamList(keyWord);
    }

    @RequestMapping("/removeExam")
    public boolean removeExam(@RequestBody String examName) {
        return examService.removeExam(examName);
    }

    @RequestMapping("/getExamByName")
    public Exam getExamByName(@RequestBody String examName) {
        return examService.getExamByName(examName);
    }
}
