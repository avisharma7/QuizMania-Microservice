package com.example.quiz_service.Model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String title;

    //one quiz can have multiple questions
    @Setter
    @ElementCollection
    private List<Integer> questionIds;

}
