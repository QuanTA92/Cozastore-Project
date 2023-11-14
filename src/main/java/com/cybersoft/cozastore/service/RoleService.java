package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.RoleEntity;
import com.cybersoft.cozastore.payload.response.RoleResponse;
import com.cybersoft.cozastore.repository.RoleRepository;
import com.cybersoft.cozastore.service.imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements RoleServiceImp {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleResponse> getAllRole() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        List<RoleResponse> roleResponses = new ArrayList<>();

        for (RoleEntity roleEntity : roleEntities){
            RoleResponse roleResponse = new RoleResponse();
            roleResponse.setIdRole(roleEntity.getId());
            roleResponse.setNameRole(roleEntity.getName());

            roleResponses.add(roleResponse);
        }
        return roleResponses;
    }
}
