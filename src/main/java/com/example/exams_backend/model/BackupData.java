package com.example.exams_backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class BackupData implements Serializable {
    private List<Exam> exams;
    private List<ExamRecord> records;
    private List<ExamRecordScore> scores;
}
