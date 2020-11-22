package com.example.demo.dao;
import com.example.demo.entity.ReturnBookSequence;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.sql.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReturnDao extends JpaRepository<ReturnBookSequence,Integer>,JpaSpecificationExecutor<ReturnBookSequence>{

	@Query(value="SELECT bookid from user_book_sequence where userid=?1 and staus='未还'",nativeQuery = true)
	public List<Integer> SelectBookid(Integer userid);
    @Query(value = "SELECT borrow_sequence_id from user_book_sequence where bookid=?1 and userid=?2 and staus='未还' limit 1",nativeQuery = true)
    public Integer selectUserBookSequence(Integer bookid,Integer userid);
    @Modifying
    @Transactional
    @Query(value = "UPDATE user_book_sequence set staus='已还',returntime=NOW() where borrow_sequence_id=?1",nativeQuery = true)
    public void updatetUserBookSequence(Integer borrow_sequence_id);
    @Modifying
    @Transactional
    @Query(value = "INSERT return_book_sequence(returntime,borrow_sequence_id) values(NOW(),?1)",nativeQuery = true)
    public void insertBookSequence(Integer borrow_sequence_id);    
    @Modifying
    @Transactional
    @Query(value = "UPDATE book set number=number+1 where id=?1",nativeQuery = true)
    public void bookNumberAdd(Integer bookid); 
    @Query(value="SELECT  book.*,a.createtime,a.deadline from book join user_book_sequence as a on a.bookid=book.id where book.id in ?1 and a.staus='未还'",nativeQuery=true)
    public Page<Map<String,Object>> notReturnBook(List<Integer> bookids, Pageable pageable);
}
