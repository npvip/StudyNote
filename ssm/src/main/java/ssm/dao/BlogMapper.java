package ssm.dao;

import ssm.bean.Blog;

import java.util.List;

/**
 * @author np
 * @date 2018/9/6
 */
public interface BlogMapper {
    List<Blog> getAllBlogs();
}
