package com.example.question_service.Controller;

import com.example.question_service.Model.Question;
import com.example.question_service.Model.QuestionWrapper;
import com.example.question_service.Model.Response;
import com.example.question_service.services.QuestionService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    //Quiz service will run this endpoint
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
    (@RequestParam String categoryName, @RequestParam Integer numQuestions) {
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }

    //Quiz service will call this endpoint
    @PostMapping("/QuestionsByIds")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds) {
        System.out.println(environment.getProperty("local.server.port")); //to print the  port
        return questionService.getQuestionsFromID(questionIds);
    }

    //quiz Service will call this endpoint

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}

//to make question service independent
// 1) find way to generate quiz
// 2) getQuestions (questionID)
// 3) getScore - earlier we did it in quiz service but since the answer is in question, we can make it here.

//Request body to test /score
//
//[
//        {
//        "id": 1,
//        "response": "Programming Language"
//        },
//        {
//        "id": 2,
//        "response": "def"
//        },
//        {
//        "id": 3,
//        "response": "Django"
//        },
//        {
//        "id": 4,
//        "response": "0"
//        },
//        {
//        "id": 5,
//        "response": "Pandas"
//        }
//        ]