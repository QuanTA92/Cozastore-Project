package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.SizeEntity;
import com.cybersoft.cozastore.payload.request.SizeRequest;
import com.cybersoft.cozastore.payload.response.SizeResponse;
import com.cybersoft.cozastore.repository.SizeRepository;
import com.cybersoft.cozastore.service.imp.SizeServiceImp;
import org.hibernate.engine.jdbc.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SizeService implements SizeServiceImp {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<SizeResponse> getSizeNameById(int idSize) {
        List<SizeEntity> sizeEntities = sizeRepository.findAll();
        List<SizeResponse> sizeResponses = new ArrayList<>();
        for (SizeEntity sizeEntity : sizeEntities){
            if(sizeEntity.getId() == idSize){
                SizeResponse sizeResponse = new SizeResponse();

                sizeResponse.setIdSize(sizeEntity.getId());
                sizeResponse.setNameSize(sizeEntity.getName());

                sizeResponses.add(sizeResponse);
            }
        }
        return sizeResponses;
    }

    @Override
    public boolean insertSize(SizeRequest sizeRequest) {
        try {
            SizeEntity size = new SizeEntity();
            size.setName(sizeRequest.getNameSize()); // Đây là tên size được lấy từ sizeRequest
            sizeRepository.save(size);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
