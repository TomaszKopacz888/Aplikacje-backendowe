package com.example.demo.Services;

import com.example.demo.Entities.ActionResponse;
import com.example.demo.Entities.FavouriteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class FavouriteService {

    @Autowired
    private AuthService authService;

    public List<FavouriteEntity> get(HttpServletRequest request) {
        if (this.authService.isUserLogged(request)) {
            try {
                FavouriteEntity entity=new FavouriteEntity();
                entity.setUserId((long)request.getSession().getAttribute("LOGGED_USER_ID"));
                 List<FavouriteEntity> favourites= getFavourites(entity);
                 if (favourites!=null){
                 return new ArrayList<>(new HashSet<>(favourites));
                 }
                 else return null;
            }
            catch (Exception e){return new ArrayList<>();}
        }
        return null;
    }


    public ActionResponse add(HttpServletRequest request, FavouriteEntity favourite) {
        if (this.authService.isUserLogged(request)){
            RestTemplate rest=new RestTemplate();
            favourite.setUserId((long)request.getSession().getAttribute("LOGGED_USER_ID"));
            try{
                if (isFavouriteExist(favourite)){
                    return new ActionResponse(false, "You already liked");
                }
                else {
                    rest.postForObject("http://127.0.0.1:8083/favourites/add/", favourite, FavouriteEntity.class);
                    return new ActionResponse(true, "Successful!");
                }

            }
            catch (Exception e){return new ActionResponse(false, "Action failed");}
        }
        return new ActionResponse(false, "You are not logged in!");
    }

    private List<FavouriteEntity> getFavourites(FavouriteEntity favourite){
        RestTemplate rest=new RestTemplate();
        return rest.postForObject("http://127.0.0.1:8083/favourites/get/", favourite, List.class);
    }

    private boolean isFavouriteExist(FavouriteEntity favourite){
        RestTemplate restTemplate=new RestTemplate();
        ActionResponse response= restTemplate.postForObject("http://127.0.0.1:8083/favourites/compare/", favourite, ActionResponse.class);
        assert response != null;
        return response.isSuccess();
    }
}
