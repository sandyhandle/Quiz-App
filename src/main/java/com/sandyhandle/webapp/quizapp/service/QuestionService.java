package com.sandyhandle.webapp.quizapp.service;

import com.sandyhandle.webapp.quizapp.model.QuestionRequest;
import com.sandyhandle.webapp.quizapp.model.data.Question;
import com.sandyhandle.webapp.quizapp.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Question> save(QuestionRequest question) {
        try {
            Question question1 = Question.builder()
                    .questionTitle(question.getQuestionTitle())
                    .option1(question.getOption1())
                    .option2(question.getOption2())
                    .rightAnswer(question.getRightAnswer())
                    .difficultyLevel(question.getDifficultyLevel())
                    .category(question.getCategory())
                    .build();

            questionRepository.save(question1);
            return new ResponseEntity<>(question1, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getquestionsbycategory(String category) {
        try{
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public List<Question> getRandomQuestionsByCategory(String category, int numQ){
        try{
            return questionRepository.findRandomQuestionsByCategory(category, numQ);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Optional<Question> findById(int id) {
        return questionRepository.findById(id);
    }
}
