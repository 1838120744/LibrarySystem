package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.BorrowBookSequence;

public interface BorrowDao extends JpaRepository<BorrowBookSequence, Integer>,JpaSpecificationExecutor<BorrowBookSequence>{
	public List<BorrowBookSequence> findAll();
	@Modifying//提示是更新删除操作
    @Transactional//更新/删除必须要开启事务
    @Query(value = "INSERT user_book_sequence(createtime,deadline,bookid,userid,borrow_sequence_id) values(NOW(),?1,?2,?3,?4)",nativeQuery = true)
    public void insertUserBookSequence(Date deadline,Integer bookid,Integer userid,Integer borrow_sequence_id);
    @Modifying
    @Transactional 
    @Query(value = "INSERT borrow_book_sequence(createtime,bookid,userid) values(NOW(),?1,?2) ",nativeQuery = true)
    public void insertBookSequence(Integer bookid,Integer userid);
    @Query(value = "SELECT @@IDENTITY ",nativeQuery = true)
    public Integer findIDENTITY();
    @Modifying
    @Transactional 
    @Query(value = "UPDATE book set number=number-1 where id=?1 and number>0",nativeQuery = true)
    public void bookNumberCut(Integer bookid);

}
