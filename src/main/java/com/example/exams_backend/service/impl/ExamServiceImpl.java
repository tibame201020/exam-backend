package com.example.exams_backend.service.impl;

import com.example.exams_backend.model.Exam;
import com.example.exams_backend.model.ExamModeParam;
import com.example.exams_backend.model.Quiz;
import com.example.exams_backend.repo.ExamRepo;
import com.example.exams_backend.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepo examRepo;

    @Override
    public boolean saveExam(Exam exam) {
        try {
            examRepo.save(exam);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean findExamByName(String name) {
        return examRepo.existsById(name);
    }

    @Override
    public List<String> getExamList(String keyWord) {
        List<String> examNameList = examRepo.findAll().stream().map(exam -> {return exam.getName();}).collect(Collectors.toList());

        if (null == keyWord || keyWord.isEmpty()) {
            return examNameList;
        } else {
            return examNameList.stream().filter(s -> { return s.contains(keyWord);}).collect(Collectors.toList());
        }
    }

    @Override
    public boolean removeExam(String name) {
        try {
            examRepo.deleteById(name);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Exam getExamByName(String examName) {
        return examRepo.findById(examName).orElse(null);
    }

    @Override
    public Quiz[] getRandomQuizzes(ExamModeParam examModeParam) {
        Quiz[] originalQuizzes = examRepo.findById(examModeParam.getName()).get().getQuizzes();
        int fullLength = originalQuizzes.length;
        int limit = examModeParam.getQuizzesNum() == 0 ? fullLength : fullLength > examModeParam.getQuizzesNum()? examModeParam.getQuizzesNum(): fullLength;

        return Arrays.stream(originalQuizzes)
                .sorted((o1, o2) -> Double.compare(Math.random(), 0.5))
                .limit(limit)
                .toArray(Quiz[]::new);
    }
}
