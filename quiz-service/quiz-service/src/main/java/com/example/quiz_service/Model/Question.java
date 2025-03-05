package com.example.quiz_service.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Column(name = "questiontitle") // Ensure this matches DB column name exactly
    private String questionTitle;

    @Getter
    @Column(name = "option1")
    private String option1;

    @Getter
    @Column(name = "option2")
    private String option2;

    @Getter
    @Column(name = "option3")
    private String option3;

    @Getter
    @Column(name = "option4")
    private String option4;

    public String getRightAnswer() {
        return rightAnswer;
    }

    @Column(name = "rightanswer")
    private String rightAnswer;

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getOption4() {
        return option4;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption1() {
        return option1;
    }

    @Column(name = "difficultylevel")
    private String difficultyLevel;

    @Column(name = "category")
    private String category;

    // ✅ Explicit Getters for JSON Serialization
    public int getId() { return id; }

}
