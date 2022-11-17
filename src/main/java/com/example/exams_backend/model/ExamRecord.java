package com.example.exams_backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@ToString
public class ExamRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String examName;
    @Lob
    private Quiz[] examQuizzes;
    @Lob
    private Quiz[] ansQuizzes;

    private Timestamp logTime;

}
