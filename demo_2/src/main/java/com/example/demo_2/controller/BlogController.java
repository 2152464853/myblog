package com.example.demo_2.controller;

import com.example.demo_2.dao.BlogDao;
import com.example.demo_2.dao.UserDao;
import com.example.demo_2.entity.Blog;
import com.example.demo_2.entity.User;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class BlogController {
    @Autowired
    BlogDao blogDao;

    @GetMapping("/blogs")
    public HashMap<String,Object> getblogs(@RequestParam("user_id") Long user_id,@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize) {

        HashMap<String,Object> res=new HashMap<>();
        int pagenum=(pageNum-1)*pageSize;
        List<Blog> blogs=blogDao.getBlogs(pagenum,pageSize);
        System.out.println(blogs.size());
        //int total=0;
        int total=blogDao.getTotal();
        //total=(int)((total+pageSize-1)/pageSize);
        //System.out.println(total);
        //System.out.println(blogs);
        res.put("data",blogs);
        res.put("status",200);
        res.put("total",total);
        return res;
    }

    @GetMapping("/blog")
    public HashMap<String,Object> getblog(@RequestParam("blogId")Long id){
        System.out.println(id);
        HashMap<String,Object> res=new HashMap<>();
        Blog blog=blogDao.getBlogById(id);
        res.put("blog",blog);
        return res;
    }


    @PostMapping("/blog/edit")
    public HashMap<String,Object> editblog(@RequestBody Blog blog){

        System.out.println(blog);
//        HashMap<String,Object> res=new HashMap<>();
//        res.put("status","error");
//        return res;
        Blog temp=null;
        if(blog.getId()!=null){
            temp=blogDao.getBlogById(blog.getId());
        }
        else{
            temp=new Blog();
            temp.setUser_id(blog.getUser_id());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        temp.setContent(blog.getContent());
        temp.setTitle(blog.getTitle());
        temp.setDescription(blog.getDescription());
        int flag=blogDao.editBlog(temp);
        System.out.println(flag);
        HashMap<String,Object> res=new HashMap<>();
        if(flag!=0){
            res.put("status","ok");
        }
        else res.put("status","error");

        return res;
    }

    @GetMapping("/blog/delete")
    public HashMap<String,Object> deleteblog(@RequestParam("id") Long id){
        System.out.println("删除"+id);
        HashMap<String,Object> res= new HashMap<>();
        int flag=blogDao.deleteBlog(id);
        if(flag!=0) res.put("status","ok");
        else res.put("status","error");

        return res;
    }
}



