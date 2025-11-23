package com.quiz.app.QuizApp.service;

import com.quiz.app.QuizApp.dao.QuestionRepo;
import com.quiz.app.QuizApp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addNewQuestion(Question question) {
        questionRepo.save(question);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        List<Question> list = questionRepo.findBycateogry(category);
        if(!list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
    }
}
