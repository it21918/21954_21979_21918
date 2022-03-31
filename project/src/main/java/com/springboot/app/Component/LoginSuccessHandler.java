package com.springboot.app.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springboot.app.model.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        String redirectURL = request.getContextPath();



        if (userDetails.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("TEACHER"))) {
            redirectURL = "teacher/home";
        } else if (userDetails.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ADMIN"))) {
            redirectURL = "admin/userList";
        } else if (userDetails.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("STUDENT"))) {
            redirectURL = "student/home";
        }

        response.sendRedirect(redirectURL);

    }

}