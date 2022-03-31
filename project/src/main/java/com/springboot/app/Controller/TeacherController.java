package com.springboot.app.Controller;

import com.springboot.app.Entity.Lesson;
import com.springboot.app.Entity.RecommendationLetter;
import com.springboot.app.Entity.Request;
import com.springboot.app.Entity.User;
import com.springboot.app.Service.LessonService;
import com.springboot.app.Service.LetterService;
import com.springboot.app.Service.RequestService;
import com.springboot.app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private LetterService letterService;

    private Request global_request;

    public User getLoginUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUserByUsername(username);
        return user;
    }

    private String getTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    @GetMapping("/home")
    public String showRequestList(Model model) {
        // create model attribute to bind form data
        User user = getLoginUser();
        List<Request> requestList = requestService.getTeacherRequests(user.getId());

        for(int i=requestList.size()-1; i>=0 ; i--) {
            if(requestList.get(i).getStatus().equals("Pending")) {
                requestList.remove(i);
            }
        }

        model.addAttribute("listRequests", requestList );
        model.addAttribute("counts",letterService.countLetters(requestList));
        return "teacher_menu";
    }

    @GetMapping("/selectRequest/{id}")
    public String selectRequest(@PathVariable(value = "id") Long id, Model model) {
        Request request =  requestService.getRequestById(id);
        global_request = request;
        List<Lesson> lessons = lessonService.getStudentLesson(request.getId());
        model.addAttribute("request",request);
        model.addAttribute("lessons",lessons);

        return "check_request";
    }

    @RequestMapping(value = "/acceptRequest",method = RequestMethod.GET)
    public String acceptRequest() {
        Request request = requestService.getRequestById(global_request.getId());
        request.setTimestamp(getTime());
        request.setStatus("Accepted");
        requestService.saveRequest(request);
        return "redirect:/teacher/writeLetter";
    }

    @RequestMapping (value = "/rejectRequest",method = RequestMethod.GET)
    public String rejectRequest() {
        Request request = requestService.getRequestById(global_request.getId());
        request.setTimestamp(getTime());
        request.setStatus("Rejected");
        requestService.saveRequest(request);
        return "redirect:/teacher/home";
    }

    @GetMapping("/writeLetter")
    public String writeLetter(Model model) {
        RecommendationLetter recommendationLetter = new RecommendationLetter();
        model.addAttribute("letter", recommendationLetter);
        return "writeLetter";
    }

    @PostMapping("/saveLetter")
    public String saveLetter(@ModelAttribute("letter") RecommendationLetter letter){
        letter.setRequests(global_request);
        letter.setTimestamp(getTime());
        letterService.saveLetter(letter);
        return "redirect:/teacher/home";
    }

}
