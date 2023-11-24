package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.CartEntity;
import com.cybersoft.cozastore.entity.ProductEntity;
import com.cybersoft.cozastore.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    List<CartEntity> findByUserAndProduct(UserEntity userEntity, ProductEntity productEntity);

}

