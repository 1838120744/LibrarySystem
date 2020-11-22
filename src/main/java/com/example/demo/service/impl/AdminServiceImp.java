package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.UserBookDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Administrator;
import com.example.demo.entity.User;
import com.example.demo.entity.UserBookSequence;
import com.example.demo.service.AdminService;
@Service("adminService")
public class AdminServiceImp implements AdminService{
	@Resource
	private UserDao userDao;
	@Resource
	private AdminDao adminDao;
	@Resource
	private UserBookDao userBookDao;
	@Override
     public List<User> findAll(){
    	 return userDao.findAll();
     }
	@Override
	public List<User> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable=PageRequest.of(page,pageSize,Sort.Direction.ASC,"id");
		Page<User> list = userDao.findAll(pageable);
		return list.getContent();// 拿到list集合
	}   
	public Long getTotal(Map<String, Object>map) {
    	return userDao.count();
    }
	public Administrator findByName(String name) {
		return adminDao.findByName(name);
	}
	@Override
	public Page<UserBookSequence> UserBookSequence_findAll(Integer page, Integer pageSize) {
		Pageable pageable=PageRequest.of(page,pageSize,Sort.Direction.ASC,"id");
		Page<UserBookSequence> list=userBookDao.findAll(pageable);
		return list;
	}
}
