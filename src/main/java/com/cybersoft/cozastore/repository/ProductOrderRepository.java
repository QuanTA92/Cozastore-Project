package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.ProductOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrderEntity, Integer> {

}
