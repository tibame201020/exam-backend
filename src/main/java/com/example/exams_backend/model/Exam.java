package com.example.exams_backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.io.Serializable;

@Entity
@Setter
@Getter
@ToString
public class Exam implements Serializable {
    @Id
    private String name;
    @Lob
    @JdbcTypeCode(SqlTypes.VARBINARY)
    @Column(columnDefinition = "LONGBLOB")
    private Quiz[] quizzes;
}
