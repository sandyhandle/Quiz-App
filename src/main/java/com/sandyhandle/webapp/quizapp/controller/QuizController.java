package com.sandyhandle.webapp.quizapp.controller;

import com.sandyhandle.webapp.quizapp.model.QuestionWrapper;
import com.sandyhandle.webapp.quizapp.model.QuizResponse;
import com.sandyhandle.webapp.quizapp.model.data.Question;
import com.sandyhandle.webapp.quizapp.model.data.Quiz;
import com.sandyhandle.webapp.quizapp.service.QuizService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@AllArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<Integer> createQuiz(@RequestParam String category,
                                             @RequestParam int numQ,
                                             @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("/get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int quizId){
        return quizService.getQuizQuestions(quizId);
    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<String> getQuizResults(@RequestBody List<QuizResponse> quizResponses,
                                                                @PathVariable Integer quizId){
        return quizService.getQuizResults(quizResponses,quizId);
    }

}
