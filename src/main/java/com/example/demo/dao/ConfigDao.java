package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Config;
import org.springframework.stereotype.Component;
@Component
public interface ConfigDao extends JpaRepository< Config,Integer> {
	
	@Query(value="select * from config  where id = ?1",nativeQuery = true)
	public  Config findId(Integer id);
	
}
