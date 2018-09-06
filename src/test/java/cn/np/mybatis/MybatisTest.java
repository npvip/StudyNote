package cn.np.mybatis;

import cn.np.mybatis.bean.Author;
import cn.np.mybatis.bean.Blog;
import cn.np.mybatis.mapper.AuthorMapper;
import cn.np.mybatis.mapper.BlogDynamicSqlMapper;
import cn.np.mybatis.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author np
 * @date 2018/9/5
 */
public class MybatisTest {

    private SqlSession sqlSession;

    @Before
    public void getSqlSession() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void closeSession() {
        sqlSession.close();
    }


    @Test
    public void selectDemo() {
        /**
         * 一种方式
         *
         * Blog blog = sqlSession.selectOne("cn.np.cn.np.cn.np.mybatis.mapper.mapper.BlogMapper.queryBlogById", 2);
         *         System.out.println(blog);
          */


        //接口式编程，动态代理实现
        BlogMapper mapper=sqlSession.getMapper(BlogMapper.class);
        Blog blog=mapper.queryBlogById(2);
        System.out.println(blog);
    }


    @Test
    public void insertDemo() {
        Blog blog = new Blog();
        blog.setTitle("这是一个标题");
        blog.setContent("这是一篇文章");
        BlogMapper mapper=sqlSession.getMapper(BlogMapper.class);
        boolean b=mapper.insertBlog(blog);

        sqlSession.commit();
        System.out.println("b=" + b);
        System.out.println(blog.getId());
    }

    @Test
    public void selectMapDemo() {

        //接口式编程，动态代理实现
        BlogMapper mapper=sqlSession.getMapper(BlogMapper.class);
        Map<Integer,Blog> blogMap=mapper.queryMapByTitle("%标题%");
        System.out.println(blogMap);
    }

    @Test
    public void selectListDemo() {

        //接口式编程，动态代理实现
        BlogMapper mapper=sqlSession.getMapper(BlogMapper.class);
        List<Blog> blogList=mapper.queryList("%标题%","id");
        for(Blog blog:blogList){
            System.out.println(blog);
        }
    }

    @Test
    public void selectBlogWithAuthorDemo() {

        //接口式编程，动态代理实现
        BlogMapper mapper=sqlSession.getMapper(BlogMapper.class);
        Blog blog=mapper.queryBlogWithAuthorById(1);
        System.out.println(blog);
        System.out.println(blog.getAuthor());
    }

    @Test
    public void selectBlogWithAuthor2Demo() {

        //接口式编程，动态代理实现
        BlogMapper mapper=sqlSession.getMapper(BlogMapper.class);
        Blog blog=mapper.queryBlogWithAuthorById2(1);
        System.out.println(blog);
        System.out.println(blog.getAuthor());
    }

    @Test
    public void selecAuthorWithBlogDemo() {

        //接口式编程，动态代理实现
        AuthorMapper mapper=sqlSession.getMapper(AuthorMapper.class);
        Author author=mapper.queryAuthorWithBlogById(1);
        System.out.println(author);
        System.out.println(author.getBlogs());
    }

    @Test
    public void selecBlogsDynamicSqlDemo() {

        //接口式编程，动态代理实现
        BlogDynamicSqlMapper mapper=sqlSession.getMapper(BlogDynamicSqlMapper.class);
        Blog blog=new Blog();
        blog.setId(1);
        blog.setTitle("%标题%");
        List<Blog> blogs=mapper.queryBlogsByTrim(blog);
        System.out.println(blogs);
    }

    @Test
    public void updateBlogsDynamicSqlDemo() {

        //接口式编程，动态代理实现
        BlogDynamicSqlMapper mapper=sqlSession.getMapper(BlogDynamicSqlMapper.class);
        Blog blog=new Blog();
        blog.setId(1);
        blog.setTitle("网易新闻");
        blog.setContent("新闻内容内容");
        boolean b=mapper.updateBlog(blog);
        sqlSession.commit();
        System.out.println(b);
    }


    //-----缓存-------//

    /**
     * 测试一级缓存效果
     */
    @Test
    public void cacheDemo() {

        //接口式编程，动态代理实现
        BlogMapper mapper1=sqlSession.getMapper(BlogMapper.class);
        Blog blog1=mapper1.queryBlogWithAuthorById(1);
        System.out.println(blog1);
        BlogMapper mapper2=sqlSession.getMapper(BlogMapper.class);
        Blog blog2=mapper2.queryBlogWithAuthorById(1);
        System.out.println(blog2);
        System.out.println(blog1==blog2);
    }

    /**
     * 测试二级缓存效果
     *
     * 会话关闭后才会更新到二级缓存
     */
    @Test
    public void cacheSecondDemo() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession1=sqlSessionFactory.openSession();
        SqlSession sqlSession2=sqlSessionFactory.openSession();


        BlogMapper mapper1=sqlSession1.getMapper(BlogMapper.class);
        Blog blog1=mapper1.queryBlogWithAuthorById(1);
        System.out.println(blog1);
        //此处若未关闭会话，sqlSession2还是会去查询数据库
        sqlSession1.close();

        BlogMapper mapper2=sqlSession2.getMapper(BlogMapper.class);
        Blog blog2=mapper2.queryBlogWithAuthorById(1);
        System.out.println(blog2);
        sqlSession2.close();

    }

}
