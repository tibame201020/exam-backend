package com.example.exams_backend.service.impl;

import com.example.exams_backend.model.Exam;
import com.example.exams_backend.repo.ExamRepo;
import com.example.exams_backend.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

        System.out.println(examNameList);

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
}
