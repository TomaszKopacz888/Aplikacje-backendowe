package com.example.demo.Services;

import com.example.demo.Entities.*;
import com.mysql.cj.log.Log;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PartyService {

    public ActionResponse add(HttpServletRequest request, PartyEntity party) {
        if (request.getSession().getAttribute("LOGGED_USER_TYPE")!=null && Objects.equals(request.getSession().getAttribute("LOGGED_USER_TYPE").toString(), "ADMIN")) {
            if (party.getTitle()==null) return new ActionResponse(false, "Fill in the title field");
            try {
                party.setUserId((Long) request.getSession().getAttribute("LOGGED_USER_ID"));
                RestTemplate rest = new RestTemplate();
                PartyEntity returnedParty=rest.postForObject("http://127.0.0.1:8083/parties/create", party, PartyEntity.class);
                return new ActionResponse(true, "Added new party!");
            }
            catch (Exception e){return  new ActionResponse(false, "Added filed");}
        }
        else return new ActionResponse(false, "Permission denied");
    }

    public PartyEntity getById(Long id) {
        try {
            RestTemplate rest=new RestTemplate();
            return rest.getForObject("http://127.0.0.1:8083/parties/"+id, PartyEntity.class);
        }
        catch (Exception e){
            return null;
        }
    }

    public List<PartyEntity> getAll(Long num) {
        try{
            RestTemplate rest=new RestTemplate();
            ResponseEntity<List<PartyEntity>> partyList= rest.exchange("http://127.0.0.1:8083/parties/all/" + num, HttpMethod.GET, null, new ParameterizedTypeReference<List<PartyEntity>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            return partyList.getBody();
        }
        catch (Exception e){return null;}
    }

    public ActionResponse updateParty(HttpServletRequest request, PartyEntity party) {
        HttpSession session=request.getSession();
        if (session.getAttribute("LOGGED_USER_ID")!=null){
            RestTemplate rest=new RestTemplate();
            long userId=(long)session.getAttribute("LOGGED_USER_ID");
            party.setUserId(userId);
            return rest.postForObject("http://127.0.0.1:8083/parties/"+party.getId()+"/update", party, ActionResponse.class);
        }
        else return new ActionResponse(false, "You are not logged in");
    }
}
