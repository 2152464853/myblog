package com.example.demo_2.dao;

import com.example.demo_2.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogDao {
    public List<Blog> getAllBlogsByUserId(@Param("user_id")Long user_id);
    public List<Blog> getBlogs(@Param("pagenum")int pagenum,@Param("pagesize")int pagesize);

    public int getTotal();

    public Blog getBlogById(@Param("id")Long id);

    public int editBlog(@Param("blog")Blog blog);

    public int deleteBlog(@Param("id")Long id);
}
