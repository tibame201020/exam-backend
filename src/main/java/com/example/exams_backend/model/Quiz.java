package com.example.exams_backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class Quiz implements Serializable {
    private String[] chooses;
    private String quizContent;
    private String[] correctContents;
    private String solution;
}
