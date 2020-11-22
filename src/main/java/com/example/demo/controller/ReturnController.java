package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.ReturnDao;
import com.example.demo.entity.ReturnBookSequence;
import com.example.demo.service.ReturnService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/book/return")
public class ReturnController {
	 @Resource
	 private ReturnService returnService;
	 @ResponseBody
     @RequestMapping("/list")
	    @RequiresPermissions("管理员")
	 public Map<String, Object> list(@RequestParam(value="page",required=false)Integer page,
	  @RequestParam(value="limit",required=false)Integer limit,HttpServletRequest request,
	  HttpServletResponse response)throws Exception{
		 Map<String, Object> map=new HashMap<String, Object>();
		 List<ReturnBookSequence> list=returnService.list(map, page-1, limit);
		 long total=returnService.getTotal(map);
		 map.put("data", list);
		 map.put("count", total);
			map.put("code", 0);
			map.put("msg", "");
		 return map;
	 }
	 @ResponseBody
		@RequestMapping("/bookid")
		public JSONObject returnbook(@RequestParam(value = "id", required = false) Integer id,HttpServletResponse httpServletResponse)throws Exception {
			JSONObject result=new JSONObject();
			result.put("msg","还书失败");
			try {
				returnService.returnbook(id);
			    result.put("msg","还书成功");
			} catch (Exception e) {
			    result.put("msg","还书失败 或 已还过书");
			}
			return result;
		}
	 @ResponseBody
	 @RequestMapping("/user/list")
	 public Map<String, Object> list(@RequestParam(value="page",required=false)Integer page
			 ,@RequestParam(value="limit",required=false)Integer limit) throws Exception {
		 Map<String, Object> mapp=new HashMap<String,Object>();
		 Page<Map<String, Object>> list=returnService.notReturnBook(page-1,limit);
		 mapp.put("data", list.getContent());
		 mapp.put("count",list.getTotalElements());
		 mapp.put("code", 0);
		 mapp.put("msg", "");
		return mapp;
	 }
}
