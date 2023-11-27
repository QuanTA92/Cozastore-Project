package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CartEntity;
import com.cybersoft.cozastore.entity.ProductEntity;
import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.payload.request.CartRequest;
import com.cybersoft.cozastore.payload.response.CartResponse;
import com.cybersoft.cozastore.repository.CartRepository;
import com.cybersoft.cozastore.repository.ProductRepository;
import com.cybersoft.cozastore.repository.UserRepository;
import com.cybersoft.cozastore.service.imp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements CartServiceImp {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean insertProductIntoCart(CartRequest cartRequest) {
        Optional<ProductEntity> product = productRepository.findById(cartRequest.getIdProduct());
        Optional<UserEntity> user = userRepository.findById(cartRequest.getIdUser());

        if (product.isPresent() && user.isPresent()) {
            List<CartEntity> existingCartItems = cartRepository.findByUserAndProduct(user.get(), product.get());

            if (!existingCartItems.isEmpty()) {
                // Handle multiple cart entries for the same user and product
                // For example, you might update the quantity of the first entry
                CartEntity firstCartItem = existingCartItems.get(0);
                int newQuantity = firstCartItem.getQuanity() + cartRequest.getQuanity();
                firstCartItem.setQuanity(newQuantity);
                cartRepository.save(firstCartItem);
            } else {
                // If no existing entry, create a new one
                CartEntity cart = new CartEntity();
                cart.setProduct(product.get());
                cart.setUser(user.get());
                cart.setQuanity(cartRequest.getQuanity());

                LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
                Date createDate = Date.from(currentDateTime.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
                cart.setCreateDate(createDate);

                try {
                    cartRepository.save(cart);
                    return true;
                } catch (Exception e) {
                    System.out.printf("Error: " + e);
                    return false;
                }
            }

            return true;
        }

        return false;
    }





    @Override
    public List<CartResponse> getCart(int idUser) {
        List<CartEntity> cartEntities = cartRepository.findAll();
        List<CartResponse> cartResponses = new ArrayList<>();
        for (CartEntity cartEntity: cartEntities){
            if (cartEntity.getUser().getId() == idUser){
                CartResponse cartTemp = new CartResponse();

                cartTemp.setCart(cartEntity.getId());
                cartTemp.setQuanity(cartEntity.getQuanity());
                cartTemp.setNameProduct(cartEntity.getProduct().getName());
                cartTemp.setImageProduct(cartEntity.getProduct().getImage());
                cartTemp.setPrice((int) cartEntity.getProduct().getPrice());
                cartResponses.add(cartTemp);

            }
        }

        return cartResponses;
    }

    @Override
    public boolean deleteCartById(int idCart) {
        if (cartRepository.existsById(idCart)) {
            cartRepository.deleteById(idCart);
            return true;
        } else {
            return false;
        }
    }
}