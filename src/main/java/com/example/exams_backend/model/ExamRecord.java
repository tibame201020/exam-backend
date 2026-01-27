package com.example.exams_backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
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
    @JdbcTypeCode(SqlTypes.VARBINARY)
    @Column(columnDefinition = "LONGBLOB")
    private Quiz[] examQuizzes;
    @Lob
    @JdbcTypeCode(SqlTypes.VARBINARY)
    @Column(columnDefinition = "LONGBLOB")
    private Quiz[] ansQuizzes;

    private Timestamp logTime;

}
