package com.quiz.app.QuizApp.dao;

import com.quiz.app.QuizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    List<Question> findBycateogry(String category);

    @Query(value ="select * from question q where q.cateogry=:category ORDER BY RANDOM() LIMIT :noQuestion" ,nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int noQuestion);
}
