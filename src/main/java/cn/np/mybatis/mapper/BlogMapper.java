package cn.np.mybatis.mapper;

import cn.np.mybatis.bean.Blog;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author np
 * @date 2018/9/5
 */
public interface BlogMapper {

    Blog queryBlogById(int id);

    boolean insertBlog(Blog blog);

    List<Blog> queryList(@Param("title")String title, @Param("colomn")String colomn);

    @MapKey("id")
    Map<Integer,Blog> queryMapByTitle(@Param("title")String title);

    Blog queryBlogWithAuthorById(@Param("id")int id);

    Blog queryBlogWithAuthorById2(@Param("id")int id);
}
