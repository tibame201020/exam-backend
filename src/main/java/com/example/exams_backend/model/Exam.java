package com.example.exams_backend.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@ToString
public class Exam implements Serializable {
    @Id
    private String name;
    @Lob
    private Quiz[] quizzes;
}

@Setter
@Getter
@ToString
class Quiz implements Serializable {
    private String[] chooses;
    private String quizContent;
    private String[] correctContents;
    private String solution;
}
