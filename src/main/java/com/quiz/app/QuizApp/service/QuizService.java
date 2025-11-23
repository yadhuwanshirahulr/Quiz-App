package com.quiz.app.QuizApp.service;

import com.quiz.app.QuizApp.dao.QuestionRepo;
import com.quiz.app.QuizApp.dao.QuizRepo;
import com.quiz.app.QuizApp.model.Question;
import com.quiz.app.QuizApp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QuestionRepo questionRepo;
    public ResponseEntity<String> createQuiz(String category, int noQuestion, String title) {
        List<Question> questions = questionRepo.findRandomQuestionByCategory(category,noQuestion);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionList(questions);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }
}
