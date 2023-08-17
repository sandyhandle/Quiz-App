package com.sandyhandle.webapp.quizapp.controller;

import com.sandyhandle.webapp.quizapp.model.QuestionRequest;
import com.sandyhandle.webapp.quizapp.model.data.Question;
import com.sandyhandle.webapp.quizapp.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {


    private final QuestionService questionService;
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @PostMapping()
    public ResponseEntity<Question> postingQuestion(@RequestBody QuestionRequest question){
        return questionService.save(question);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Question>> getquestionsbycategory(@PathVariable String category){
        return questionService.getquestionsbycategory(category);
    }
}
