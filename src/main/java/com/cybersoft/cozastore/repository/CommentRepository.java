package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.BlogEntity;
import com.cybersoft.cozastore.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByBlog(BlogEntity blogEntity);
}
