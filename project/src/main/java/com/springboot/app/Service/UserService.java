package com.springboot.app.Service;


import java.util.List;
import java.util.Optional;

import com.springboot.app.Entity.*;
import com.springboot.app.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private LessonRepository repoLesson;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RoleRepository repoRole;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LetterRepository letterRepository;

    @Override
    public List<User> listAll() {
        return (List<User>) repoUser.findAll();
    }

    public List<Role> listRoles() {
        return repoRole.findAll();
    }

    @Override
    public void save(User user) throws Exception {
        if (repoUser.countUsersWithUsername(user.getUsername(),user.getId()) >= 1) {
            throw new Exception();
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            repoUser.save(user);
        }
    }

    @Override
    public User get(Long id) {
        return repoUser.findById(id).get();
    }

    public User getUserByUsername(String username) {
        return repoUser.getUserByUsername(username);
    }

    public List<User> getAllTeachers() {
        List<User> userList = (List<User>) repoUser.findAll();
        Role role = repoRole.findByName("TEACHER");
        for (int i = userList.size(); i>0; i--) {
            if (!userList.get(i-1).getRoles().contains(role)) {
                userList.remove(userList.get(i-1));
            }
        }
        return userList;
    }

    @Override
    public void delete(Long id) {
        List<Lesson> lessons = repoLesson.getStudentLesson(id);
        List<Lesson> lessons2 = repoLesson.getTeacherLesson(id);
        for(int i =0 ; i<lessons.size();i++){
            repoLesson.deleteById(lessons.get(i).getId());
        }
        for(int i =0 ; i<lessons2.size();i++){
            repoLesson.deleteById(lessons2.get(i).getId());
        }

        List<RecommendationLetter> recommendationLetters = letterRepository.getStudentLettersById(id);
        List<RecommendationLetter> recommendationLetters2 = letterRepository.getTeacherLettersById(id);
        for(int i =0 ; i<recommendationLetters.size();i++){
            letterRepository.deleteById(recommendationLetters.get(i).getId());
        }
        for(int i =0 ; i<recommendationLetters2.size();i++){
            letterRepository.deleteById(recommendationLetters2.get(i).getId());
        }

        List<Request> requestList = requestRepository.getStudentRequests(id);
        List<Request> requestList2 = requestRepository.getTeachersRequests(id);
        for(int i =0 ; i<requestList.size();i++){
            requestRepository.deleteById(requestList.get(i).getId());
        }
        for(int i =0 ; i<requestList2.size();i++){
            requestRepository.deleteById(requestList2.get(i).getId());
        }

        repoUser.getUserById(id).removeRoles();


        repoUser.deleteById(id);
    }
}
