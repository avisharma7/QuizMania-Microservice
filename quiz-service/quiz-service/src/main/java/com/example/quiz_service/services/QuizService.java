package com.example.quiz_service.services;


import com.example.question_service.Model.QuestionWrapper;
import com.example.quiz_service.DAO.QuizDao;
import com.example.quiz_service.Model.Quiz;
import com.example.question_service.Model.Response;
import com.example.quiz_service.feign.QuizInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    private static final Logger log = LoggerFactory.getLogger(QuizService.class);


    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

//    @Autowired
//    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
        //quiz service need to interact with question service


        System.out.println("Called from the Service class");
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody(); //since it's a ResponseEntity

//
//        List<Question> questions =  questionDao.findRandomQuestionByCategory(category, numQ);
//
//
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);


        return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);

    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        System.out.println("Called from the Service class");
        Quiz quiz = quizDao.findById(id).get();
        System.out.println(quiz);
        List<Integer>questionIds = quiz.getQuestionIds();

        ResponseEntity<List<com.example.question_service.Model.QuestionWrapper>> questions = quizInterface.getQuestionFromId(questionIds);
        return questions;
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        log.info("Called from the Service class");
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
