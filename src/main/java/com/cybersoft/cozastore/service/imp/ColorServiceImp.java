package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.ColorRequest;
import com.cybersoft.cozastore.payload.response.ColorResponse;

import java.util.List;

public interface ColorServiceImp {

    List<ColorResponse> getColorNameById(int idColor);

    boolean insertNewColor(ColorRequest colorRequest);

    List<ColorResponse> getAllColor();


}
