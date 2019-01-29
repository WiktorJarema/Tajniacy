package org.tajniacy.service;

import org.tajniacy.model.Word;

import java.util.List;

public interface WordService {

    List<Word> get25RandomWords();

    List<Word> getNRandomWordsByLanguage(int numberOfWords, String language);
}
