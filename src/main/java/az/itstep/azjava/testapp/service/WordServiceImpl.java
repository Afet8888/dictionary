package az.itstep.azjava.testapp.service;

import az.itstep.azjava.testapp.model.User;
import az.itstep.azjava.testapp.model.Word;
import az.itstep.azjava.testapp.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class WordServiceImpl implements WordService {

    private WordRepository wordRepository;
    private UserService userService;

    @Override
    public Word save(Word word, String token) {
        if(Objects.isNull(token))throw new RuntimeException("No token");
        User user = userService.findByToken(token);
        if(Objects.isNull(user)) throw new RuntimeException("No user found on entered token");
        if(Objects.isNull(word) || Objects.isNull(word.getId()))
            throw new RuntimeException("Wrong data");
        if(Objects.isNull(word.getAz()) || Objects.isNull(word.getEn()))
            throw new RuntimeException("Wrong word Data");
        return wordRepository.save(word);
    }

    @Override
    public String translateFromEn(String en) {
        String word =  wordRepository.findByEn(en)
                .map(Word::getAz)
                .orElse(null);
        if(Objects.isNull(word))
            throw new RuntimeException("Word not found");
        return word;
    }

    @Override
    public String translateFromAz(String az) {
        //BAD PRACTICE
//        List<Word> wordList = (List<Word>) wordRepository.findAll();
//        return wordList.stream()
//                .filter(w -> Objects.equals(w.getAz(), az))
//                .findFirst()
//                .map(Word::getEn)
//                .orElse(null);
        //BEST PRACTICE
        String word = wordRepository.findByAz(az)
                .map(Word::getEn)
                .orElse(null);
        if(Objects.isNull(word))
            throw new RuntimeException("Word not found");
        return word;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setWordRepository(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }
}
