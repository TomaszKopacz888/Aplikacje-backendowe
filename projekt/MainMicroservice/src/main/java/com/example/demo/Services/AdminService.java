package com.example.demo.Services;

import com.example.demo.Entities.ActionResponse;
import com.example.demo.Entities.AdminId;
import com.example.demo.Entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Service
public class AdminService {

    public ActionResponse setAdmin(HttpServletRequest request, AdminId id) {
        if (isAdmin(request)) {
            RestTemplate rest = new RestTemplate();
            try {
                boolean response=Boolean.TRUE.equals(rest.postForObject("http://127.0.0.1:8082/users/makeAdmin", id, boolean.class));
                if (response) {
                    return new ActionResponse(true, "Successful!");
                }
                else return new ActionResponse(false, "Action false");
            } catch (Exception e) {
                return new ActionResponse(false, e.getMessage());
                //return new ActionResponse(false, "Action false");
            }
        } else return new ActionResponse(false, "You are not admin");
    }

    private boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userType=(String) session.getAttribute("LOGGED_USER_TYPE");
        return Objects.equals(userType, "ADMIN");
    }
}
