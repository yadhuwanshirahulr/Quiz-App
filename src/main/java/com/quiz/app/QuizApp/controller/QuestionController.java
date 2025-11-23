package com.quiz.app.QuizApp.controller;

import com.quiz.app.QuizApp.model.Question;
import com.quiz.app.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionService.getAllQuestions();
    }
    @PostMapping
    public ResponseEntity<String> addNewQuestion(@RequestBody Question question){
        return questionService.addNewQuestion(question);

    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }
}
