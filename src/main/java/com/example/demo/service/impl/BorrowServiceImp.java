package com.example.demo.service.impl;

import java.util.Calendar;
import java.util.Date;
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

import com.example.demo.dao.BookDao;
import com.example.demo.dao.BorrowDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.BorrowBookSequence;
import com.example.demo.entity.User;
import com.example.demo.service.BorrowService;
@Service
public class BorrowServiceImp implements BorrowService{

	@Resource
	private BorrowDao borrowDao;
	@Override
	public List<BorrowBookSequence> list(Map<String, Object> map, Integer page, Integer pagesize) {
		Pageable pageable=PageRequest.of(page, pagesize,Sort.Direction.ASC,"id");
		Page<BorrowBookSequence> listPage=borrowDao.findAll(pageable);
        return listPage.getContent();
 	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return borrowDao.count();
	}
	@Override
    @Transactional
	public void borrowbook(Integer bookid) throws Exception{
		User currentUser=(User)SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		Integer userid=currentUser.getId();
		System.out.println(userid);
		Date createtime=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(createtime);
		calendar.add(Calendar.MONTH,1);
		Date deadline=calendar.getTime();
		borrowDao.insertBookSequence( bookid, userid);
		Integer borrow_sequence_id=borrowDao.findIDENTITY();
		System.out.println(borrow_sequence_id);
		borrowDao.insertUserBookSequence(deadline, bookid, userid, borrow_sequence_id);
		borrowDao.bookNumberCut(bookid);
		// TODO Auto-generated method stub
		
	}

}
