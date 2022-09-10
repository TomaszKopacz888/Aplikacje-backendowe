package com.example.demo.Services;

import aj.org.objectweb.asm.TypeReference;
import com.example.demo.Entities.ActionResponse;
import com.example.demo.Entities.FavouriteEntity;
import com.example.demo.Entities.PartyEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class FavouriteService {

    @Autowired
    private AuthService authService;
    @Autowired
    private PartyService partyService;

    public List<PartyEntity> get(HttpServletRequest request) {
        if (this.authService.isUserLogged(request)) {
            try {
                FavouriteEntity entity=new FavouriteEntity();
                entity.setUserId((long)request.getSession().getAttribute("LOGGED_USER_ID"));
                List<FavouriteEntity> favourites= map(getFavourites(entity));

                if (favourites!=null){
                    List<PartyEntity> parties=new ArrayList<>();
                        //parties.add(preparePartiesList((FavouriteEntity) fav));

                    favourites.forEach(favourite ->parties.add(this.partyService.getById(favourite.getPartyId())));
                    return parties;
                }
                else return null;
            }
            catch (Exception e){return new ArrayList<>();}
        }
        return null;
    }

    private List<FavouriteEntity> map(List<FavouriteEntity> list){
        ObjectMapper mapper=new ObjectMapper();
        List<FavouriteEntity> fav=new ArrayList<>();
        for (int i=0; i<list.size(); i++){
            fav.add(mapper.convertValue(list.toArray()[i], FavouriteEntity.class));
        }
        return fav;
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
