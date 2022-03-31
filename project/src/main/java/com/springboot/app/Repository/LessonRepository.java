package com.springboot.app.Repository;

import com.springboot.app.Entity.Lesson;
import com.springboot.app.Entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("SELECT r FROM Lesson r WHERE r.requests.sender.id = ?1")
    public List<Lesson> getStudentLesson(Long id) ;

    @Query("SELECT r FROM Lesson r WHERE r.requests.receiver.id = ?1")
    public List<Lesson> getTeacherLesson(Long id) ;


}
