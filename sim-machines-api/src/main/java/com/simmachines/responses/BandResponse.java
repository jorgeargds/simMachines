package com.simmachines.responses;

import com.simmachines.entities.UserEntity;
import java.util.List;

public class BandResponse {

    String name;
    List<UserEntity> users;

    public BandResponse(String name, java.util.List<UserEntity> users) {
        this.name =name;
        this.users = users;
    }
}
