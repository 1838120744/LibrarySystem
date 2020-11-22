package com.example.demo.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import com.example.demo.entity.Book;
import com.example.demo.entity.BookType;


public interface BookDao extends JpaRepository<Book,Integer>,JpaSpecificationExecutor<Book> {
    public List<Book> findAll();
    public Book findById(int id);
    public List<Book> findByBookType(Optional<BookType> bookType);
    
}
