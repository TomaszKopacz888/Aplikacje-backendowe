package com.example.demo.Controllers;

import com.example.demo.Entities.ActionResponse;
import com.example.demo.Entities.FavouriteEntity;
import com.example.demo.Repositories.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/favourites")
public class FavouritesController {

    @Autowired
    private FavouriteRepository favouriteRepository;

    //TODO: FAVOURITE
    @PostMapping(
            value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<FavouriteEntity> addFavourite(@RequestBody FavouriteEntity favourite) {
        return ResponseEntity.ok(this.favouriteRepository.save(favourite));
    }

    @PostMapping(
            value = "/get",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
//    public ResponseEntity<List<FavouriteEntity>> getFavourite(@RequestBody FavouriteEntity userId) {
//        return ResponseEntity.ok(this.favouriteRepository.findAllByUserId(userId.getUserId()));
//    }
    public List<FavouriteEntity> getFavourite(@RequestBody FavouriteEntity userId){
        return this.favouriteRepository.findAllByUserId(userId.getUserId());
    }


    @PostMapping(
            value = "/compare",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ActionResponse getByUserIdAndPartyId(@RequestBody FavouriteEntity fav){
        FavouriteEntity favResponse=this.favouriteRepository.findByUserIdAndPartyId(fav.getUserId(), fav.getPartyId());
        if (favResponse==null) return  new ActionResponse(false, "No favourites");
        return new ActionResponse(true, "Success!");
    }
}
