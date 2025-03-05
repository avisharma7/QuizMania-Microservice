package com.example.question_service.services;

import com.example.question_service.DAO.QuestionDao;
import com.example.question_service.Model.Question;
import com.example.question_service.Model.QuestionWrapper;
import com.example.question_service.Model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service

public class QuestionService {
     private static final Logger log = LoggerFactory.getLogger(QuestionService.class);
     @Autowired
     QuestionDao questionDao;
     public ResponseEntity<List<Question>> getAllQuestions() {
          try {
               return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
          }catch (Exception e){
               e.printStackTrace();
          }
          return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
     }

     public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
          try {
               return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
          }catch (Exception e){
               e.printStackTrace();
          }
          return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

     }

     public ResponseEntity<String> addQuestion(Question question) {
          questionDao.save(question);
          return new ResponseEntity<>("success",HttpStatus.CREATED);
     }

     public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
          List<Integer> questions = questionDao.findRandomQuestionByCategory(categoryName, numQuestions);
          return new ResponseEntity<>(questions,HttpStatus.OK);
     }

     public ResponseEntity<List<QuestionWrapper>> getQuestionsFromID(List<Integer> questionIds) {
          List<QuestionWrapper> wrappers = new ArrayList<>();
          List<Question> questions = new ArrayList<>();
          for (Integer questionId : questionIds) {
               questions.add(questionDao.findById(questionId).get());
          }
          //we need wrapper of questions not question because, we want to hide the answers
          for(Question question : questions) {
               QuestionWrapper questionWrapper = new QuestionWrapper();
               questionWrapper.setId(question.getId());
               questionWrapper.setQuestionTitle(question.getQuestionTitle());
               questionWrapper.setOption1(question.getOption1());
               questionWrapper.setOption2(question.getOption2());
               questionWrapper.setOption3(question.getOption3());
               questionWrapper.setOption4(question.getOption4());
               wrappers.add(questionWrapper);
          }
          return new ResponseEntity<>(wrappers,HttpStatus.OK);
     }

     public ResponseEntity<Integer> getScore(List<Response> responses) {
          int right = 0;
          for (Response response : responses) {
               Question question = questionDao.findById(response.getId()).get();
               if(response.getResponse().equals(question.getRightAnswer())){
                    right++;
               }
          }
          return new ResponseEntity<>(right,HttpStatus.OK);
     }
}
