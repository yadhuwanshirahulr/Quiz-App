package com.quiz.app.QuizApp.service;

import com.quiz.app.QuizApp.dao.QuestionRepo;
import com.quiz.app.QuizApp.dao.QuizRepo;
import com.quiz.app.QuizApp.model.Question;
import com.quiz.app.QuizApp.model.QuestionWrapper;
import com.quiz.app.QuizApp.model.Quiz;
import com.quiz.app.QuizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        quizRepo.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questionsfromDB = quiz.get().getQuestionList();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for(Question q:questionsfromDB){
            QuestionWrapper question = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionWrappers.add(question);
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Question> questions = quiz.getQuestionList();
        int i=0,cnt = 0;
        for(Response res:response){
            if(res.getResponse().equals(questions.get(i).getRightAnswer())){
                cnt++;
            }
            i++;
        }
        return new ResponseEntity<>(cnt,HttpStatus.OK);
    }
}
