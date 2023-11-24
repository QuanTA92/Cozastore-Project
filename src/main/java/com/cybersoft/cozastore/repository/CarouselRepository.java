package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.CarouselEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarouselRepository extends JpaRepository<CarouselEntity, Integer> {

}
