package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.demo.entity.User;
import com.example.demo.entity.UserBookSequence;

public interface UserService {

	public void deleteById(int userid);
	public Optional<User> findById(Integer id);
	public User save(User user);
	public Page<UserBookSequence> findByUserid(Integer page,Integer pageSize);
}
