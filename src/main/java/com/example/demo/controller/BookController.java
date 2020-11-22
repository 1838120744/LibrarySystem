package com.example.demo.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.annotations.Parameter;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookType;
import com.example.demo.entity.BorrowBookSequence;
import com.example.demo.service.BookService;
import com.example.demo.service.BookTypeService;

import org.springframework.web.bind.annotation.PathVariable;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/book")
public class BookController {
	@Resource
	private BookService bookService;
	
	@RequestMapping("/add")
	String add() {
		
		return "/book/add";
	}
	@RequestMapping("/image")
	String Image() {
		return "/book/image";
	}
	@RequestMapping("/library")
	String library() {
		return "/book/library";
	}
	@RequestMapping("/edit")
    @RequiresPermissions("管理员")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		Book book=bookService.findById(id);
		mav.addObject("book",book);//	var save_url = [[${save_url}]];
		mav.addObject("save_url", "/user/update?id=" + id);
		mav.setViewName("/book/update");
		return mav;
	}
	@ResponseBody
	 @RequestMapping("upload")
    @RequiresPermissions("管理员")
	 public Map<String,Object> upload(MultipartFile file,HttpServletRequest request){
	        String prefix="";
	        String dateStr="";
	        //保存上传
	        OutputStream out = null;
	        InputStream fileInput=null;
	        try{
	            if(file!=null){
	                String originalName = file.getOriginalFilename();
	                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
	                Date date = new Date();
	                String uuid = UUID.randomUUID()+"";
	                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                dateStr = simpleDateFormat.format(date);
	                String filepath = "C:\\Users\\lenovo\\Documents\\workspace-spring-tool-suite-4-4.1.0.RELEASE\\LibrarySystem\\src\\main\\resources\\static\\images\\" + dateStr+"\\"+uuid+"." + prefix;
	                File files=new File(filepath);
	                //打印查看上传路径
	                System.out.println(filepath);
	                if(!files.getParentFile().exists()){
	                    files.getParentFile().mkdirs();
	                }
	                file.transferTo(files);
	                Map<String,Object> map2=new HashMap<>();
	                Map<String,Object> map=new HashMap<>();
	                map.put("code",0);
	                map.put("msg","");
	                map.put("data",map2);
	                map2.put("src","/images/"+ dateStr+"/"+uuid+"." + prefix);
	                return map;
	            }

	        }catch (Exception e){
	        }finally{
	            try {
	                if(out!=null){
	                    out.close();
	                }
	                if(fileInput!=null){
	                    fileInput.close();
	                }
	            } catch (IOException e) {
	            }
	        }
	        Map<String,Object> map=new HashMap<>();
	        map.put("code",1);
	        map.put("msg","");
	        return map;

	    }
	  @ResponseBody
      @RequestMapping("/go_add")
	    @RequiresPermissions("管理员")
      public JSONObject go_add(@Valid Book book,BindingResult bindingResult)throws Exception {
    	  JSONObject result=new JSONObject();
    	  if(bindingResult.hasErrors()) {
    		  result.put("message",bindingResult.getFieldError().getDefaultMessage());
    		  System.out.println(result.get("message"));
    	  }
    	  else {
    		  bookService.save(book);
			result.put("message", "操作成功！");
		}
    	  return result;
      }  
	  @ResponseBody
	  @RequestMapping("/delete")
	    @RequiresPermissions("管理员")
		public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
				throws Exception {
			String[] idsStr = ids.split(",");
			JSONObject result = new JSONObject();
			for (int i = 0; i < idsStr.length; i++) {
				bookService.deleteById(Integer.parseInt(idsStr[i]));
			}
			result.put("success", true);
			return result;
		}
	  @ResponseBody
	  @RequestMapping("/booktype/{id}")
	  public Map<String, Object> BookType(@PathVariable(value="id") Integer id,
			 HttpServletResponse response,
				HttpServletRequest request) throws Exception{
		  Map<String, Object>map =new HashMap<String,Object>();
		  List<Book> list=bookService.findByBookType(id);
		  long total=bookService.getTotal(map);
		  map.put("data", list);
		  map.put("count", total);
		  map.put("msg", "");
		  map.put("code",0);
		  System.out.println(11);
		  return map;
	  }
	  @ResponseBody
	  @RequestMapping("/booktype/list")
	  public Map<String, Object> booktypeList(){
		  Map<String, Object> map=new HashMap<String,Object>();
		  List<BookType> list=bookService.findAll();
		  map.put("data",list);
		  return map;
	  }
	  @ResponseBody
	    @RequestMapping("/list")
	    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
	    		@RequestParam(value = "limit", required = false) Integer limit, 
				HttpServletResponse response,
				HttpServletRequest request) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Book> booklist = bookService.list(map, page-1, limit);
			long total = bookService.getTotal(map);
			map.put("data", booklist);
			map.put("count", total);
			map.put("code", 0);
			map.put("msg", "请先登录");
			return map;
		}
}
