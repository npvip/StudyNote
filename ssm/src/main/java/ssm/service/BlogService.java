package ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.bean.Blog;
import ssm.dao.BlogMapper;

import java.util.List;

/**
 * @author np
 * @date 2018/9/6
 */
@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public List<Blog> getAllBlogs(){
        return blogMapper.getAllBlogs();
    }

}
