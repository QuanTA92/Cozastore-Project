package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.response.UserResponse;

import java.util.List;

public interface UserServiceImp {
    List<UserResponse> getAllUser();

    boolean deleteUserById(int idUser);

    boolean updateUserById(int idUser, String userName, int idRole);

    List<UserResponse> getUserById(int idUser);
}
