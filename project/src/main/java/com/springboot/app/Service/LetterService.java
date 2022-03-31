package com.springboot.app.Service;

import com.springboot.app.Entity.RecommendationLetter;
import com.springboot.app.Entity.Request;
import com.springboot.app.Repository.LetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetterService {

    @Autowired
    LetterRepository letterRepository;

    public void saveLetter(RecommendationLetter letter) {
        letterRepository.save(letter);
    }

    public RecommendationLetter getLetterById(Long id) {
        return letterRepository.getRecommendationLetterById(id);
    }

    public List<RecommendationLetter> getStudentLettersById(Long id) {
        return letterRepository.getStudentLettersById(id);
    }

    public String[] countLetters(List<Request> requestList) {
        String[] list = new String[requestList.size()];
        for (int i = 0; i < requestList.size(); i++) {
            if (letterRepository.countLetters(requestList.get(i).getId()) == 0) {
               list[i] = "No";
            } else if (letterRepository.countLetters(requestList.get(i).getId()) > 0) {
                list[i] = "Yes";
            } else {
                list[i] = "null";
            }
        }
        return list;
    }

}
