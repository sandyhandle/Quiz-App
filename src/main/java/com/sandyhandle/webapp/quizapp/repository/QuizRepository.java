package com.sandyhandle.webapp.quizapp.repository;

import com.sandyhandle.webapp.quizapp.model.data.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
