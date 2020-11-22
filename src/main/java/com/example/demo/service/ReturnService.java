package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ReturnBookSequence;

public interface ReturnService {
     public List<ReturnBookSequence> list(Map<String,Object>map,Integer page,Integer pagesize);
     public Long getTotal(Map<String, Object>map);
	public void returnbook(Integer id) throws Exception;
	public Page<Map<String, Object>> notReturnBook(Integer page,Integer pagesize) throws Exception;
}
