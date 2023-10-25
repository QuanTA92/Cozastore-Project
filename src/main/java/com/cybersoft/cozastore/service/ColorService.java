package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.ColorEntity;
import com.cybersoft.cozastore.payload.request.ColorRequest;
import com.cybersoft.cozastore.payload.response.ColorResponse;
import com.cybersoft.cozastore.repository.ColorRepository;
import com.cybersoft.cozastore.service.imp.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorService implements ColorServiceImp {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<ColorResponse> getColorNameById(int idColor) {
        List<ColorEntity> colorEntities = colorRepository.findAll();
        List<ColorResponse> colorResponses = new ArrayList<>();

        for (ColorEntity colorEntity : colorEntities) {
            if (colorEntity.getId() == idColor) {
                ColorResponse colorResponse = new ColorResponse();
                colorResponse.setIdColor(colorEntity.getId());
                colorResponse.setNameColor(colorEntity.getName());

                colorResponses.add(colorResponse);
            }

        }
        return colorResponses;
    }

    @Override
    public boolean insertNewColor(ColorRequest colorRequest) {
        try {
            ColorEntity colorEntity = new ColorEntity();
            colorEntity.setName(colorRequest.getNameColor());
            colorRepository.save(colorEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
