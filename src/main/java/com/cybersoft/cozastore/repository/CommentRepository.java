package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
