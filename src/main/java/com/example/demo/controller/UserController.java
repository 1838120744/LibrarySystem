package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.shiro.subject.*;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.entity.UserBookSequence;
import com.example.demo.service.UserService;
import com.example.demo.shiro.LoginToken;
import com.example.demo.util.CryptographyUtil;

import net.sf.json.JSON;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/user")
public class UserController{
	
	@Resource
	private UserService userService;
	
	/**
	 * /user/login
	 */
	@RequestMapping("/myself")
	public String myself() {
		return "/user/myself";
	}
	@RequestMapping("/borrowbook")
	public String booksearch() {
		return "/user/borrowbook";
	}
	@RequestMapping("/booktype")
	public String booktype() {
		return "/user/booktype";
	}
	@RequestMapping("/returnbook")
	public String returnbook() {
		return "/user/returnbook";
	}
	@RequestMapping("test")
	public String Test() {
		return "/user/test";
	}
	@RequestMapping("/login")
	public String login() {
		return "/user/login";
	}
	@RequestMapping("/register")
	public String register() {
		return "user/register";
	}
	@RequestMapping("/main")
	public String main() {
		return "/user/main";
	}
	@RequestMapping("/userbook")
	public String userbook() {
		return "/user/userbook";
	}
	@RequestMapping("update")
	public String update() {
		return "/user/update";
	}
	@RequestMapping("change_password")
	public String change_password() {
		return "/user/change_password";
	}
	@ResponseBody
	@RequestMapping("/book/list")
	public Map<String, Object> bookList(@RequestParam(value="page",required=false)Integer page,
			@RequestParam(value="limit",required=false)Integer limit){
		Map<String, Object> map=new HashMap<String,Object>();
		Page<UserBookSequence> list=userService.findByUserid(page-1,limit);
		map.put("data", list.getContent());
		  map.put("count", list.getTotalElements());
		  map.put("msg", "");
		  map.put("code",0);
		return map;
	}
	@ResponseBody
	@RequestMapping("/go_login")
	public JSONObject go_login(String id,String password)throws Exception {
		JSONObject result = new JSONObject();
		//获取 subject
		Subject subject=SecurityUtils.getSubject();
		//封装用户数据
		LoginToken token=new LoginToken(id,password,"user");
	     System.out.println("user:"+id+" "+password);
		try {
			//执行登陆  shiro的登陆
			subject.login(token);
			//执行登陆  shiro的登陆
			result.put("success", true);
			result.put("msg","登陆成功");
			User user = userService.findById(Integer.valueOf(id)).orElse(null);
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", user); //把当前用户信息存到session中
		} catch (UnknownAccountException e) {
			result.put("success", false);
			result.put("msg","用户名不存在");
		}catch (IncorrectCredentialsException e) {
			result.put("success", false);
			result.put("msg","密码错误");
		}catch (NumberFormatException e) {
			result.put("success", false);
			result.put("msg","账号只能包含数字");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/go_register")
	public JSONObject Add(@Valid User user,BindingResult bindingResult)throws Exception {
		JSONObject result=new JSONObject();
		if(bindingResult.hasErrors()) {
			result.put("message", bindingResult.getFieldError().getDefaultMessage());
		}
		else {
			userService.save(user);
			result.put("message", "添加成功！");
		}
		return result;
	}
	 @RequestMapping("/edit")
		public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
			ModelAndView mav = new ModelAndView();
			User user=userService.findById(id).orElse(null);
			mav.addObject("user",user);//	var save_url = [[${save_url}]];
			mav.addObject("save_url", "/user/update?id=" + id);
			mav.setViewName("/user/update");
			return mav;
		}
	@ResponseBody
	@RequestMapping("/go_update")
	public JSONObject update(@RequestParam(value = "id", required = false) Integer id,@Valid User user,BindingResult bindingResult)throws Exception {
		JSONObject result=new JSONObject();
		if(bindingResult.hasErrors()) {
			result.put("message", bindingResult.getFieldError().getDefaultMessage());
		}
		else {
			userService.save(user);
			if(id==((User)SecurityUtils.getSubject().getSession().getAttribute("currentUser")).getId())
				SecurityUtils.getSubject().getSession().setAttribute("currentUser",user);
			result.put("message", "修改成功！");
		}
		return result;
	}
	@RequestMapping("/logout")
	public String logout()throws Exception{
		SecurityUtils.getSubject().logout(); //shiro的退出
		return "redirect:/index";
	}	
}
