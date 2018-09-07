package com.simmachines.controllers;

import com.google.gson.Gson;
import com.simmachines.entities.UserEntity;
import com.simmachines.repositories.UserRepository;
import com.simmachines.responses.BandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository up;

    Gson gson = new Gson();

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    ResponseEntity<Object> getAllUsers() {
        List<UserEntity> users = up.getAllUsers();
        return new ResponseEntity<>(gson.toJson(users), HttpStatus.OK);
    }

    @RequestMapping(value = "/getTopTenUsersByBand", method = RequestMethod.GET)
    ResponseEntity<Object> getTopTenUsersByBand() {
        List<UserEntity> users = up.getAllUsers();
        List<UserEntity> topTenUsers = new ArrayList<>();
        users.stream()
                .forEach(user -> {
                    int bandIndex = user.getBands().indexOf("the beatles");
                    if (bandIndex != -1)
                        if (user.getFrequencies() != null) {
                            this.verifyList(topTenUsers, user, bandIndex);
                        }
                });

        return new ResponseEntity<>(gson.toJson(topTenUsers), HttpStatus.OK);
    }

    @RequestMapping(value = "/getMostPopularBand", method = RequestMethod.GET)
    ResponseEntity<Object> getMostPopularBand() {
        HashMap bands = new HashMap<String, List<UserEntity>>();
        List<UserEntity> users = up.getAllUsers();
        List<UserEntity> usersMostPopularBand = new ArrayList<>();
        users.stream()
                .forEach(user -> {
                    addBands(bands, user);
                });

        BandResponse band  = this.getMostPopularBand(bands);
        return new ResponseEntity<>(gson.toJson(band), HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserWhoSharedBands", method = RequestMethod.GET)
    ResponseEntity<Object> getUserWhoSharedBands() {
        List<UserEntity> users = up.getAllUsers();
        List<UserEntity> usersWhoSharedBands = new ArrayList<>();


        return new ResponseEntity<>(gson.toJson(usersWhoSharedBands), HttpStatus.OK);
    }


    private void verifyList(List<UserEntity> topTenUsers, UserEntity user, int bandIndex) {
        for (int i = 0; i < 10; i++) {
            if (topTenUsers.isEmpty()) {
                topTenUsers.add(user);
                break;
            } else if (topTenUsers.size() < 10) {
                topTenUsers.add(user);
                break;
            } else {
                int firstBandIndex = topTenUsers.get(i).getBands().indexOf("the beatles");
                if (topTenUsers.get(i).getFrequencies().get(firstBandIndex) < user.getFrequencies().get(bandIndex)) {
                    topTenUsers.add(user);
                    topTenUsers.remove(i);
                    break;
                }
            }
        }
    }

    private void addBands(HashMap<String, List<UserEntity>> bands, UserEntity user) {
        user.getBands().stream()
                .forEach(band -> {
                    List<UserEntity> usersPerBand = bands.get(band);
                    if (usersPerBand == null) {
                        List<UserEntity> users = new ArrayList<>();
                        users.add(user);
                        bands.put(band, users);

                    } else
                        usersPerBand.add(user);

                });


    }

    private BandResponse getMostPopularBand(HashMap<String, List<UserEntity>> bands) {
        Iterator<Map.Entry<String, List<UserEntity>>> iterator = bands.entrySet().iterator();
        String mostPopularBand = "";
        while (iterator.hasNext()) {
            Map.Entry<String, List<UserEntity>> band = iterator.next();
            if (mostPopularBand == "")
                mostPopularBand = band.getKey();
            else {
                if (bands.get(mostPopularBand).size() < band.getValue().size()) {
                    mostPopularBand = band.getKey();
                }
                ;
            }
        }
        return new BandResponse(mostPopularBand, bands.get(mostPopularBand));
    }

}