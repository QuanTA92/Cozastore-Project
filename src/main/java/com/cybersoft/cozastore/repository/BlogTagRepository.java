package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.BlogEntity;
import com.cybersoft.cozastore.entity.BlogTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogTagRepository extends JpaRepository<BlogTagEntity, Integer> {
    List<BlogTagEntity> findByBlog(BlogEntity blogEntity);
}
