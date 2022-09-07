package com.example.demo.Services;

import com.example.demo.Entities.FavouriteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteService {

    @Autowired
    private AuthService authService;

    public List<FavouriteEntity> get(HttpServletRequest request) {
        if (this.authService.isUserLogged(request)) {
            try {
                FavouriteEntity tmp=new FavouriteEntity();
                long id=(long)request.getSession().getAttribute("LOGGED_USER_ID");
                tmp.setUserId(id);
                RestTemplate rest = new RestTemplate();
                 return rest.postForObject("http://127.0.0.1:8083/favourites/get/", tmp, List.class);

            }
            catch (Exception e){return new ArrayList<>();}
        }
        return null;
    }


}
