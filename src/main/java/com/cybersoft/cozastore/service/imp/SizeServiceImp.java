package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.SizeRequest;
import com.cybersoft.cozastore.payload.response.SizeResponse;

import java.util.List;

public interface SizeServiceImp {
    List<SizeResponse> getSizeNameById(int idSize);

    boolean insertSize(SizeRequest sizeRequest);

}
