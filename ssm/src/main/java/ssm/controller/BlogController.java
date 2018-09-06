package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ssm.bean.Blog;
import ssm.service.BlogService;

import java.util.List;
import java.util.Map;

/**
 * @author np
 * @date 2018/9/6
 */
@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/listBlog")
    public String blogListPage(Map<String,Object> map){
        List<Blog> blogList=blogService.getAllBlogs();
        map.put("allBlog",blogList);
        return "list";
    }
}
