package com.example.demo.dao;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.From;
import javax.websocket.Decoder.Binary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Administrator;
import com.example.demo.entity.User;

import net.bytebuddy.implementation.bind.annotation.Super;
public interface AdminDao extends JpaRepository<Administrator, String>,JpaSpecificationExecutor<Administrator> {
       public Administrator findByName(String name);
}
