package com.cybersoft.cozaStore.service.imp;

import com.cybersoft.cozaStore.payload.request.CartRequest;
import com.cybersoft.cozaStore.payload.response.CartResponse;
import com.cybersoft.cozaStore.repository.CartRepository;

import java.util.List;

public interface CartServiceImp {
    boolean insertProductIntoCart(CartRequest cartRequest);

    List<CartResponse> getCartByIdUser(int idUser);


    boolean deleteProductById(int idProduct);

    boolean deleteCartByIdUser(int idUser);

    boolean deleteCartById(int id);

    boolean updateCart(int id, int id_product, int quanity, int id_user);
}
