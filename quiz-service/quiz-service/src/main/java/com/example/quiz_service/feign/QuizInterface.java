package com.example.quiz_service.feign;

import com.example.question_service.Model.QuestionWrapper;
import com.example.question_service.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    //THESE THREE METHODS WILL BE CALLED FROM QUIZ SERVICE TO QUESTIONS SEERVICE
    //Quiz service will run this endpoint
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
    (@RequestParam String categoryName, @RequestParam Integer numQuestions);

    //Quiz service will call this endpoint
    @PostMapping("question/QuestionsByIds")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds);

    //quiz Service will call this endpoint

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
