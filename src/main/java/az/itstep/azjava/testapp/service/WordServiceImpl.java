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
        User user = userService.findByToken(token);
        if(Objects.isNull(word) || Objects.nonNull(word.getId()))
            throw new RuntimeException();
        if(Objects.isNull(word.getAz()) || Objects.isNull(word.getEn()))
            throw new RuntimeException("Wrong Data");
        return wordRepository.save(word);
    }

    @Override
    public String translateFromEn(String en) {
        return wordRepository.findByEn(en)
                .map(Word::getAz)
                .orElse(null);
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
        return wordRepository.findByAz(az)
                .map(Word::getEn)
                .orElse(null);
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
