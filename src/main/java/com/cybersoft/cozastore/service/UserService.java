package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.RoleEntity;
import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.payload.response.ProductResponse;
import com.cybersoft.cozastore.payload.response.UserResponse;
import com.cybersoft.cozastore.repository.RoleRepository;
import com.cybersoft.cozastore.repository.UserRepository;
import com.cybersoft.cozastore.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<UserResponse> getAllUser() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        for (UserEntity userEntity : userEntities){
            UserResponse userResponse = new UserResponse();

            userResponse.setIdUser(userEntity.getId());
            userResponse.setUserName(userEntity.getUsername());
            userResponse.setEmail(userEntity.getEmail());
            userResponse.setIdRole(userEntity.getId());
            userResponse.setNameRole(userEntity.getRole().getName());
            userResponses.add(userResponse);
        }

        return userResponses;
    }

    @Override
    public boolean deleteUserById(int idUser) {
        if(userRepository.existsById(idUser)){

            userRepository.deleteById(idUser);

            return true;
        }

        return false;
    }

    @Override
    public boolean updateUserById(int idUser, String userName, int idRole) {
        Optional<UserEntity> optionalUser = userRepository.findById(idUser);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            user.setUsername(userName);

            RoleEntity role = roleRepository.findById(idRole).orElse(null);
            if (role != null) {
                user.setRole(role);
            }

            userRepository.save(user);
            return true;
        }

        return false;
    }



    @Override
    public List<UserResponse> getUserById(int idUser) {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        for (UserEntity userEntity : userEntities){
            if(userEntity.getId() == idUser){
                UserResponse userResponse = new UserResponse();

                userResponse.setIdUser(userEntity.getId());
                userResponse.setUserName(userEntity.getUsername());
                userResponse.setEmail(userEntity.getEmail());
                userResponse.setIdRole(userEntity.getRole().getId());
                userResponse.setNameRole(userEntity.getRole().getName());

                userResponses.add(userResponse);
            }
        }
        return userResponses;
    }

}
