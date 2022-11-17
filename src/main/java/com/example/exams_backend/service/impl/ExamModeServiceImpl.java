package com.example.exams_backend.service.impl;

import com.example.exams_backend.model.*;
import com.example.exams_backend.repo.ExamRecordRepo;
import com.example.exams_backend.repo.ExamRecordScoreRepo;
import com.example.exams_backend.service.ExamModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamModeServiceImpl implements ExamModeService {
    @Autowired
    private ExamRecordRepo examRecordRepo;

    @Autowired
    private ExamRecordScoreRepo examRecordScoreRepo;


    @Override
    public ExamRecord generateNewRecord(Quiz[] quizzes, ExamModeParam examModeParam) {
        deleteUnValidRecord();

        ExamRecord examRecord = new ExamRecord();
        examRecord.setExamName(examModeParam.getName());
        examRecord.setExamQuizzes(quizzes);
        examRecordRepo.save(examRecord);

        Quiz[] testQuizzes = new Quiz[quizzes.length];
        for (int i = 0; i < testQuizzes.length; i++) {
            Quiz quiz = quizzes[i];
            quiz.setCorrectContents(new String[0]);
            quiz.setSolution(null);
            testQuizzes[i] = quiz;
        }
        Arrays.stream(testQuizzes).forEach(quiz -> {
            quiz.setChooses(Arrays.stream(quiz.getChooses()).sorted((o1, o2) -> Double.compare(Math.random(), 0.5)).toArray(String[]::new));
        });
        examRecord.setExamQuizzes(null);
        examRecord.setAnsQuizzes(testQuizzes);

        return examRecord;
    }

    @Override
    public ExamRecord saveExamRecord(ExamRecord examRecord) {

        Quiz[] ansQuizzes = examRecord.getAnsQuizzes();
        long id = examRecord.getId();

        ExamRecord recordFromDb = examRecordRepo.findById(id).orElse(new ExamRecord());
        if (recordFromDb.getExamName().isEmpty()) {
            return recordFromDb;
        } else {
            recordFromDb.setAnsQuizzes(ansQuizzes);
            recordFromDb.setLogTime(new Timestamp(System.currentTimeMillis()));
            examRecordRepo.save(recordFromDb);
            setExamRecordToScore(recordFromDb);

            return recordFromDb;
        }
    }

    @Override
    public ExamRecordScore getExamRecordScoreById(long id) {
        return examRecordScoreRepo.findById(id).orElse(new ExamRecordScore());
    }

    @Override
    public List<ExamRecordScore> getScoreList(String keyword) {
        List<ExamRecordScore> examRecordScores = examRecordScoreRepo.findAll();
        Collections.reverse(examRecordScores);
        if ("getScoreByKeyword".equals(keyword)) {
            return examRecordScores;
        } else {
            return examRecordScores.stream().filter(examRecordScore -> examRecordScore.getExamName().contains(keyword)).collect(Collectors.toList());
        }
    }

    @Override
    public boolean deleteRecordScore(long id) {
        try {
            examRecordRepo.deleteById(id);
            examRecordScoreRepo.deleteById(id);
            deleteUnValidRecord();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ExamRecord findById(long id) {
        return examRecordRepo.findById(id).orElse(new ExamRecord());
    }

    private void setExamRecordToScore(ExamRecord examRecord){
        Quiz[] ansQuizzes = examRecord.getAnsQuizzes();
        Quiz[] examQuizzes = examRecord.getExamQuizzes();

        int totalQuizNums = ansQuizzes.length;
        int correctNums = 0;

        for (int i = 0; i < totalQuizNums; i++) {
            List<String> userSelects = Arrays.stream(ansQuizzes[i].getCorrectContents()).sorted().collect(Collectors.toList());
            List<String> correctContents = Arrays.stream(examQuizzes[i].getCorrectContents()).sorted().collect(Collectors.toList());
            if (userSelects.equals(correctContents)) {
                correctNums++;
            }
        }

        String score = correctNums + "/" + totalQuizNums;

        ExamRecordScore examRecordScore = new ExamRecordScore();
        examRecordScore.setId(examRecord.getId());
        examRecordScore.setExamName(examRecord.getExamName());
        examRecordScore.setLogTime(examRecord.getLogTime());
        examRecordScore.setCorrectNums(correctNums);
        examRecordScore.setQuizNums(totalQuizNums);
        examRecordScore.setScore(score);

        examRecordScoreRepo.save(examRecordScore);
    }

    private void deleteUnValidRecord() {
        List<ExamRecord> examRecords = examRecordRepo.findByAnsQuizzesIsNull();
        examRecordRepo.deleteAll(examRecords);
    }
}
