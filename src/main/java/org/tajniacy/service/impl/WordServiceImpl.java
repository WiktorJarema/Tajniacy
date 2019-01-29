package org.tajniacy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tajniacy.model.Word;
import org.tajniacy.repository.WordJpaRepository;
import org.tajniacy.service.WordService;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordJpaRepository wordJpaRepository;


    @Override
    public List<Word> get25RandomWords() {
        return wordJpaRepository.find25RandomWords();
    }

    @Override
    public List<Word> getNRandomWordsByLanguage(int numberOfWords, String language) {
        return wordJpaRepository.findNRandomWordsByLanguage(numberOfWords, language);
    }
}
