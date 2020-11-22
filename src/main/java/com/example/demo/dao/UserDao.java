package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

public interface UserDao extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {
	public User findByName(String name);
    public List<User> findAll();
    public Optional<User> findById(Integer id);
}
