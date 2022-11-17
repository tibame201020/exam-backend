package com.example.exams_backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Setter
@Getter
@ToString
public class ExamModeParam implements Serializable {
    private String name;
    private Integer quizzesNum;
}
