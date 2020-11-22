package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserBookDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.entity.UserBookSequence;
import com.example.demo.service.UserService;

@Service("userService")
public class UserServiceImp implements UserService{

	@Resource
	private UserDao userDao;
	@Resource
	private UserBookDao userBookDao;
	public Optional<User> findById(Integer id) {
		return userDao.findById(id);
	}
	public User save(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}
	@Override
	public void deleteById(int id) {
           userDao.deleteById(id);
	}
	@Override
	public Page<UserBookSequence> findByUserid(Integer page,Integer pageSize) {
		User currentUser=(User)SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		Integer userid=currentUser.getId();
		Pageable pageable=PageRequest.of(page,pageSize,Sort.Direction.ASC,"id");
		return userBookDao.findByUserid(userid,pageable);
	}

}
