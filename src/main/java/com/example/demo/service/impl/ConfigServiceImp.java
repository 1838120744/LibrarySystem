package com.example.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ConfigDao;
import com.example.demo.entity.Config;
import com.example.demo.service.ConfigService;
@Service("configService")
public class ConfigServiceImp implements ConfigService {
	
	@Resource
	private ConfigDao configDao;
	
	@Override
	public Config findById(Integer id) {
		//return configDao.getOne(id);
		return configDao.findId(id);
	}

}
