package az.itstep.azjava.testapp.controller;

import az.itstep.azjava.testapp.model.Word;
import az.itstep.azjava.testapp.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/translate")
public class TranslateController {

    private WordService wordService;

    @GetMapping("/en/{word}")
    String translateFromEn(@PathVariable String word) {
        return wordService.translateFromEn(word);
    }

    @GetMapping("/az/{word}")
    String translateFromAz(@PathVariable String word) {
        return wordService.translateFromAz(word);
    }


    @ExceptionHandler (RuntimeException.class)
    String handleTranslate(RuntimeException e) {
        return e.getMessage();
    }

    @Autowired
    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }
}
