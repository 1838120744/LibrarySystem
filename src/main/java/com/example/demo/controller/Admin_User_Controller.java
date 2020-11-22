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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;
import com.example.demo.entity.UserBookSequence;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/user")
public class Admin_User_Controller {
	@Resource
	private AdminService adminService;
	@Resource
	private UserService userService;
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("管理员")
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
    		@RequestParam(value = "limit", required = false) Integer limit, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> userList = adminService.list(map, page-1, limit);
		long total = adminService.getTotal(map);
		map.put("data", userList);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	@ResponseBody
	@RequestMapping("/delete")
    @RequiresPermissions("管理员")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			userService.deleteById(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		return result;
	}
	@ResponseBody
    @RequestMapping("/book/list")
    @RequiresPermissions("管理员")
    public Map<String, Object> bookList(@RequestParam(value = "page", required = false) Integer page,
    		@RequestParam(value = "limit", required = false) Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<UserBookSequence> list=adminService.UserBookSequence_findAll(page-1,limit);
		map.put("data", list.getContent());
		map.put("count",list.getTotalElements());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
}
