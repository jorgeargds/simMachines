package com.simmachines.repositories;

import com.simmachines.entities.UserEntity;
import com.simmachines.utilities.DataUtility;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public class UserRepository {

    DataUtility dataUtility = new DataUtility();
    List<UserEntity> users = dataUtility.getUsersFromInputFile();

    public List<UserEntity> getAllUsers() {
        return users;
    }

}
