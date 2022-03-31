package com.springboot.app.Service;

import com.springboot.app.Repository.LessonRepository;
import com.springboot.app.Entity.Lesson;
import com.springboot.app.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    @Autowired
    private LessonRepository repoLesson ;


    public List<Lesson> getAllLessons() {
        return repoLesson.findAll();
    }

    public void saveLesson(Lesson lesson) {
        this.repoLesson.save(lesson);
    }

    public List<Lesson> getStudentLesson(Long id) {
        List<Lesson> lessons = getAllLessons();
        for (int i = lessons.size(); i>0; i--) {
            if (!lessons.get(i-1).getRequests().getId().equals(id)) {
                lessons.remove(lessons.get(i-1));
            }
        }
        return lessons;
    }

}
