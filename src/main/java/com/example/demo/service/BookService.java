package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.dao.BookDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.BookType;
import com.example.demo.entity.BorrowBookSequence;

public interface BookService{
    public List<Book> list(Map<String,Object> map,Integer page,Integer pageSize);
	public Book save(Book book);
	public void deleteById(int id);
	public long getTotal(Map<String, Object> map);
    public Book findById(int id);
	public List<Book> findByBookType(Integer id);
	public void returnbook(Integer bookid);
	public List<BookType> findAll();
}
