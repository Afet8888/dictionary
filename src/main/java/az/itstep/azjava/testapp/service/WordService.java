package az.itstep.azjava.testapp.service;

import az.itstep.azjava.testapp.model.Word;

public interface WordService {

    Word save(Word word, String token);

    String translateFromEn(String en);

    String translateFromAz(String az);
}
