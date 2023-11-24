package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.CarouselRequest;

import java.io.IOException;

public interface CarouselServiceImp {

    boolean insertCarousel(CarouselRequest carouselRequest) throws IOException;
}
