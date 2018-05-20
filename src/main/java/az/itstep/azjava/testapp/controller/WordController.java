package az.itstep.azjava.testapp.controller;

import az.itstep.azjava.testapp.model.User;
import az.itstep.azjava.testapp.model.Word;
import az.itstep.azjava.testapp.service.UserService;
import az.itstep.azjava.testapp.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/words")
public class WordController {

    private WordService wordService;
    private UserService userService;

    @PostMapping
    Word save(@RequestBody Word word, @RequestHeader HttpHeaders headers) {
        try {
            User user = userService.findByToken(headers.get("token").get(0));
            if(Objects.isNull(user))
                return null;
        } catch (NullPointerException ex) {
            return null;
        }
        return wordService.save(word);
    }

    @Autowired
    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
