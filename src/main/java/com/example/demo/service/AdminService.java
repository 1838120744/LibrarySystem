package com.example.demo.service;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

import com.example.demo.entity.Administrator;
import com.example.demo.entity.User;
import com.example.demo.entity.UserBookSequence;

public interface AdminService {
    public List<User> findAll();
    public List<User> list(Map<String,Object> map,Integer page,Integer pageSize);
    public Long getTotal(Map<String, Object>map);
	public Administrator findByName(String name);
	public Page<UserBookSequence> UserBookSequence_findAll(Integer page, Integer pageSize);
}
