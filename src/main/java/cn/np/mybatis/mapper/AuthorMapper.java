package cn.np.mybatis.mapper;

import cn.np.mybatis.bean.Author;
import org.apache.ibatis.annotations.Param;

/**
 * @author np
 * @date 2018/9/5
 */
public interface AuthorMapper {

    Author queryAuthorById(@Param("id") int id);

    Author queryAuthorWithBlogById(@Param("id") int id);
}
