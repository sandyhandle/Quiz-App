package com.sandyhandle.webapp.quizapp.service;

import com.sandyhandle.webapp.quizapp.model.QuestionWrapper;
import com.sandyhandle.webapp.quizapp.model.QuizResponse;
import com.sandyhandle.webapp.quizapp.model.data.Question;
import com.sandyhandle.webapp.quizapp.model.data.Quiz;
import com.sandyhandle.webapp.quizapp.repository.QuestionRepository;
import com.sandyhandle.webapp.quizapp.repository.QuizRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionService questionService;


    public ResponseEntity<Integer> createQuiz(String category, int numQ, String title) {
        Quiz quiz = Quiz.builder()
                .questionList(questionService.getRandomQuestionsByCategory(category,numQ))
                .title(title)
                .build();

        quizRepository.save(quiz);
        return new ResponseEntity<>(quiz.getId(), HttpStatus.CREATED);



    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);


        List<Question> questions = quiz.get().getQuestionList();

        List<QuestionWrapper> questionsForUsers = new ArrayList<>();

        for(Question q : questions){
            QuestionWrapper qw = QuestionWrapper.builder()
                    .id(q.getId())
                    .questionTitle(q.getQuestionTitle())
                    .option1(q.getOption1())
                    .option2(q.getOption2())
                    .build();
            questionsForUsers.add(qw);
        }

        return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
    }

    public ResponseEntity<String> getQuizResults(List<QuizResponse> quizResponses, Integer quizId) {
        Quiz quiz = quizRepository.findById(quizId).get();
        List<Question> questions = quiz.getQuestionList();
        int count = 0;

        for(QuizResponse q: quizResponses){
            Optional<Question> question = questions.stream()
                    .filter(c -> c.getId().equals(q.getId()))
                    .findFirst()         ;
            if(q.getResponse().equals( question.get().getRightAnswer()) ) {
                count++;
            }
        }

        return new ResponseEntity<>(count + "/" + quizResponses.size() + " in the quiz", HttpStatus.OK);
    }
}
