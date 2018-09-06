package cn.np.mybatis.mapper;

import cn.np.mybatis.bean.Blog;

import java.util.List;

/**
 * @author np
 * @date 2018/9/6
 */
public interface BlogDynamicSqlMapper {

    List<Blog> queryBlogsByIf(Blog blog);

    List<Blog> queryBlogsByWhere(Blog blog);

    List<Blog> queryBlogsByTrim(Blog blog);

    List<Blog> queryBlogsByChoose(Blog blog);

    boolean updateBlog(Blog blog);
}
