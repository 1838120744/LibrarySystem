package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.UserBookSequence;

public interface UserBookDao extends JpaRepository<UserBookSequence,Integer>,JpaSpecificationExecutor<UserBookSequence>{
     public Page<UserBookSequence> findByUserid(Integer userid,Pageable pageable);
     public List<UserBookSequence> findAll();
}
