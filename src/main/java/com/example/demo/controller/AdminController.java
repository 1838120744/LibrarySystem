package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Administrator;
import com.example.demo.entity.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.impl.AdminServiceImp;
import com.example.demo.shiro.LoginToken;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @ResponseBody
    @RequestMapping("/go_login")
	public JSONObject go_login(String name,String password)throws Exception {
	     System.out.println("admin:"+name+" "+password);
    	JSONObject result = new JSONObject();
		//获取 subject
		Subject subject=SecurityUtils.getSubject();
		//封装用户数据
		LoginToken token=new LoginToken(name,password,"admin");
		try {
			//执行登陆  shiro的登陆
			subject.login(token);
			//执行登陆  shiro的登陆
			result.put("success", true);
			result.put("msg","登陆成功");
			Administrator administrator = adminService.findByName(name);
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", administrator); //把当前用户信息存到session中
		} catch (UnknownAccountException e) {
			result.put("success", false);
			result.put("msg","用户名不存在");
		}catch (IncorrectCredentialsException e) {
			result.put("success", false);
			result.put("msg","密码错误");
		}
		return result;
    }
    @RequestMapping("/login")
    public String login() {
       	return "/admin/login";
    }
    @RequestMapping("/user_manage")
    public String user_manage() {
    	return "/admin/user_manage";
    }
    @RequestMapping("/book_manage")
    public String book_manage() {
    	return "/admin/book_manage";
    }
    @RequestMapping("/main")
    public String main() {
       	return "/admin/main";
    }
    @RequestMapping("/borrow_manage")
    public String borrow_manage(){
    	return "/admin/borrow_manage";
    }
    @RequestMapping("/return_manage")
    public String return_manage(){
    	return "/admin/return_manage";
    }

    @RequestMapping("/userbook_manage")
    public String userbook_manage(){
    	return "/admin/userbook_manage";
    }
    @RequestMapping("/logout")
	public String logout()throws Exception{
		SecurityUtils.getSubject().logout(); //shiro的退出
		return "redirect:admin/login";
	}
}
