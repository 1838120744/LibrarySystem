package com.example.demo.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.BookTypeDao;
import com.example.demo.dao.BorrowDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.BookType;
import com.example.demo.entity.User;
import com.example.demo.service.BookService;


@Service("bookService")
public class BookServiceImp implements BookService{
	@Resource
	private BookDao bookDao;
	@Resource
	private BookTypeDao bookTypeDao;
	@Resource
	private BorrowDao borrowDao;
	public List<Book> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable=PageRequest.of(page,pageSize,Sort.Direction.ASC,"id");
		Page<Book> list = bookDao.findAll(pageable);
		return list.getContent();// 拿到list集合
	}
	public Book save(Book book) {
		return bookDao.save(book);
	}
	public long getTotal(Map<String, Object> map) {
		return bookDao.count();
	}
	@Override
	public void deleteById(int id) {
         bookDao.deleteById(id);		
	}
	@Override
	public Book findById(int id) {
		return bookDao.findById(id);
	}
	@Override
	public List<Book> findByBookType(Integer id) {
		Optional<BookType> bookType=bookTypeDao.findById(id);
		return bookDao.findByBookType(bookType);
	}	
	@Override
	@Transactional
	public void returnbook(Integer bookid) {
		User currentUser=(User)SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		Integer userid=currentUser.getId();
		Date returntime=new Date();	
	}
	@Override
	public List<BookType> findAll() {
		// TODO Auto-generated method stub
		return bookTypeDao.findAll();
	}
}
