package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.StatusRequest;
import com.cybersoft.cozastore.payload.response.StatusResponse;

import java.util.List;

public interface StatusServiceImp {

    List<StatusResponse> getAllStatus();

    boolean insertStatus(StatusRequest statusRequest);



}
