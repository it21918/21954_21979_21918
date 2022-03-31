package com.springboot.app.Repository;

import com.springboot.app.Entity.RecommendationLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LetterRepository extends JpaRepository<RecommendationLetter, Long> {

    @Query("SELECT COUNT(l.id) FROM RecommendationLetter l WHERE l.requests.id=:id")
    public Long countLetters( Long id);

    @Query("SELECT l FROM RecommendationLetter l WHERE l.id=:id")
    public RecommendationLetter getRecommendationLetterById(Long id);

    @Query("SELECT r FROM RecommendationLetter r WHERE r.requests.receiver.id = ?1")
    public List<RecommendationLetter> getTeacherLettersById(Long id) ;

    @Query("SELECT r FROM RecommendationLetter r WHERE r.requests.sender.id = ?1")
    public List<RecommendationLetter> getStudentLettersById(Long id) ;
}