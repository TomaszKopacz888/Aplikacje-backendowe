package com.example.demo.Services;

import com.example.demo.Entities.ActionResponse;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Entities.UserLoginRequest;
import com.example.demo.Entities.UserLoginResponse;
import com.example.demo.Exceptions.NoUserException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Service
public class AuthService {

    public boolean isUserLogged(HttpSession session) {
        Long loggedUserId = this.getLoggedUserId(session);
        return loggedUserId != null;
    }

    public boolean isUserLogged(HttpServletRequest request) {
        return this.isUserLogged(request.getSession());
    }

    public Long getLoggedUserId(HttpSession session) {
        Object sessionAttribute=session.getAttribute("LOGGED_USER_ID");
            if (session.getAttribute("LOGGED_USER_ID")!=null) {
            if (sessionAttribute instanceof Long) {
                return (Long) sessionAttribute;
            } else if (sessionAttribute instanceof String) {
                return Long.parseLong((String) sessionAttribute);
            }
        }
        return (Long) sessionAttribute;
    }


    public ActionResponse loginUser(HttpServletRequest request, UserLoginRequest data) throws NullPointerException {
        HttpSession session = request.getSession();
        if (this.isUserLogged(session)) {
            return new ActionResponse(false, "You are alreatdy logged in. Please logout, and try again");
        }
        try {
            RestTemplate rest = new RestTemplate();
            long id = rest.postForObject("http://127.0.0.1:8082/users/getIdByEmailAndPassword", data, int.class);
            UserEntity user=rest.getForObject("http://127.0.0.1:8082/users/"+id,  UserEntity.class);
            session.setAttribute("LOGGED_USER_ID", id);
            assert user != null;
            session.setAttribute("LOGGED_USER_TYPE", user.getType());
            return new ActionResponse(true, "Login successful");
        } catch (Exception e) {
            return new ActionResponse(false, "Incorrect email or password");
        }

    }

    public ActionResponse logoutUser(HttpServletRequest request) {
        HttpSession session=request.getSession();
        if (isUserLogged(session)){
            session.removeAttribute("LOGGED_USER_ID");
            return new ActionResponse(true, "Logout successful!");
        }
        else return new ActionResponse(false, "You are not logged in");
    }

    public ActionResponse registerUser(HttpServletRequest request, UserEntity user) {
        try{
            RestTemplate rest=new RestTemplate();
            if (user.getEmail()==null) return new ActionResponse(false, "You must enter your email");
            if (user.getPassword()==null) return new ActionResponse(false, "You must enter your password");
            UserEntity user2=rest.postForObject("http://127.0.0.1:8082/users/create", user, UserEntity.class);
            return new ActionResponse(true, "Registration successful");
        }
        catch (Exception e){
            return new ActionResponse(false, "This email is already in use");
        }
    }

    public ActionResponse updateUser(HttpServletRequest request, UserEntity user) {
        HttpSession session=request.getSession();
        if (isUserLogged(session)){
            RestTemplate rest=new RestTemplate();
            long id=(long)session.getAttribute("LOGGED_USER_ID");
            user.setId(id);
            UserEntity restUser=rest.postForObject("http://127.0.0.1:8082/users/"+id+"/update", user, UserEntity.class);
            return new ActionResponse(true, "Updating data successful");
        }
        else return new ActionResponse(false, "You are not logged in");
    }

}
