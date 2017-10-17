package dictation.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dictation.model.QuestionModel;
import dictation.service.QuestionService;

@Controller
public class DictationController {
	
	@Autowired
	QuestionService questionService;

    @GetMapping("/dictations/{id}")
    public String index(Model model, @PathVariable String id) throws IOException {
    	int intId = Integer.parseInt(id);

    	if(intId >= questionService.getQuestionCount()) {
    		// idが問題数を超えている場合は、0番目の問題にリダイレクトする
    		return "redirect:/dictations/0";
    	}
    	QuestionModel question = questionService.getQuestion(intId);
        model.addAttribute("question", question);

        return "dictation";
    }
}