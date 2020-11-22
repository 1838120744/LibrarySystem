package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ReturnDao;
import com.example.demo.entity.ReturnBookSequence;
import com.example.demo.entity.User;
import com.example.demo.service.ReturnService;
@Service
public class ReturnServiceImp implements ReturnService{

	@Resource
	private ReturnDao returnDao;
	@Override
	public List<ReturnBookSequence> list(Map<String, Object> map, Integer page, Integer pagesize) {
		Pageable pageable=PageRequest.of(page, pagesize,Sort.Direction.ASC,"id");
		Page<ReturnBookSequence> list=returnDao.findAll(pageable);
		return list.getContent();
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return returnDao.count();
	}
	@Override
	@Transactional
	public void returnbook(Integer bookid) throws Exception{
        User currentUser=(User)SecurityUtils.getSubject().getSession().getAttribute("currentUser");
        Integer userid=currentUser.getId();
        Integer borrow_sequence_id=returnDao.selectUserBookSequence(bookid, userid);
        if(borrow_sequence_id==null) 
        	throw new Exception("borrow_sequence_id 不能为空");
        returnDao.updatetUserBookSequence(borrow_sequence_id);
        returnDao.insertBookSequence(borrow_sequence_id);
        returnDao.bookNumberAdd(bookid);
	}

	@Override
	public Page<Map<String, Object>> notReturnBook(Integer page,Integer pagesize) throws Exception{
		User currentUser=(User)SecurityUtils.getSubject().getSession().getAttribute("currentUser");
        Integer userid=currentUser.getId();
        Pageable pageable=PageRequest.of(page, pagesize);
        List<Integer> bookids=returnDao.SelectBookid(userid);
        System.out.println(bookids);
		Page<Map<String, Object>> map=returnDao.notReturnBook(bookids,pageable);
		return map;
	}
    
}
