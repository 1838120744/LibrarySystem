package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.BorrowBookSequence;
import com.example.demo.service.BorrowService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/book/borrow")
public class BorrowController {
	@Resource
	private BorrowService borrowService ;
	@ResponseBody
	@RequestMapping("/list")
    @RequiresPermissions("管理员")
	public Map<String, Object> borrow_list(@RequestParam(value="page",required=false) Integer page,
			 @RequestParam(value="limit",required=false)Integer limit,HttpServletRequest request,
			 HttpServletResponse response)throws Exception{
		 Map<String, Object> map=new HashMap<String,Object>();
		 List<BorrowBookSequence> list = borrowService.list(map, page-1, limit);
			long total = borrowService.getTotal(map);
			map.put("data", list);
			map.put("count", total);
			map.put("code", 0);
			map.put("msg", "");
		 return map;
	 }
	@ResponseBody
	@RequestMapping("/bookid")
	public JSONObject borrowbook(@RequestParam(value = "id", required = false) Integer id,HttpServletResponse httpServletResponse)throws Exception {
		JSONObject result=new JSONObject();
		result.put("msg","借书失败");
		try {
			borrowService.borrowbook(id);
			result.put("msg","借书成功");
		} catch (Exception e) {
			result.put("msg",e.getMessage());
		}
		return result;
	}
}
