package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.SizeEntity;
import com.cybersoft.cozastore.entity.StatusEntity;
import com.cybersoft.cozastore.payload.request.StatusRequest;
import com.cybersoft.cozastore.payload.response.StatusResponse;
import com.cybersoft.cozastore.repository.StatusRepository;
import com.cybersoft.cozastore.service.imp.StatusServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StatusService implements StatusServiceImp {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<StatusResponse> getAllStatus() {
        List<StatusEntity> statusEntities = statusRepository.findAll();
        List<StatusResponse> statusResponses = new ArrayList<>();

        for (StatusEntity statusEntity : statusEntities){
            StatusResponse statusResponse = new StatusResponse();
            statusResponse.setNameStatus(statusEntity.getName());
            statusResponse.setCreateDate(statusEntity.getCreateDate());

            statusResponses.add(statusResponse);
        }

        return statusResponses;
    }

    @Override
    public boolean insertStatus(StatusRequest statusRequest) {
        try {
        StatusEntity status = new StatusEntity();
        status.setName(statusRequest.getNameStatus());

        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        Date createDate = Date.from(currentDateTime.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
        status.setCreateDate(createDate);

        statusRepository.save(status);

        return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
