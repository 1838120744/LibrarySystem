package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.BorrowBookSequence;

public interface BorrowService {
    public List<BorrowBookSequence> list(Map<String, Object>map,Integer page,Integer pagesize);
    public Long getTotal(Map<String, Object>map);
	void borrowbook(Integer bookid) throws Exception;
}
