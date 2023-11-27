package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {

    TagEntity findByName(String name);

}
