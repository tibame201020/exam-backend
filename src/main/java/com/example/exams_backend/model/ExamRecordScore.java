package com.example.exams_backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@ToString
public class ExamRecordScore implements Serializable {
    @Id
    private long id;
    private String examName;
    private int correctNums;
    private int quizNums;
    private String score;
    private Timestamp logTime;
}
