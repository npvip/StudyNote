package cn.np.mybatis;

import cn.np.mybatis.bean.Author;
import cn.np.mybatis.bean.Blog;
import cn.np.mybatis.mapper.AuthorMapper;
import cn.np.mybatis.mapper.BlogDynamicSqlMapper;
import cn.np.mybatis.mapper.BlogMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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


    /**
     * 获取 SqlSessionFactory
     *
     */
    public static SqlSessionFactory getSqlSessionFactory(){
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        return  sqlSessionFactory;

    }

    @Test
    public void selectDemo() {
        SqlSession sqlSession=getSqlSessionFactory().openSession();

        /**
         * 一种方式
         *
         * Blog blog = sqlSession.selectOne("cn.np.cn.np.cn.np.mybatis.mapper.mapper.BlogMapper.queryBlogById", 2);
         *         System.out.println(blog);
          */


        //接口式编程，动态代理实现
        BlogMapper mapper=sqlSession.getMapper(BlogMapper.class);
        System.out.println("mapper:"+mapper);
        Blog blog=mapper.queryBlogById(2);
        System.out.println(blog);

        sqlSession.close();
    }


    @Test
    public void insertDemo() {
        SqlSession sqlSession=getSqlSessionFactory().openSession();

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
        SqlSession sqlSession=getSqlSessionFactory().openSession();

        //接口式编程，动态代理实现
        BlogMapper mapper=sqlSession.getMapper(BlogMapper.class);
        Map<Integer,Blog> blogMap=mapper.queryMapByTitle("%标题%");
        System.out.println(blogMap);
    }

    @Test
    public void selectListDemo() {
        SqlSession sqlSession=getSqlSessionFactory().openSession();

        //接口式编程，动态代理实现
        BlogMapper mapper=sqlSession.getMapper(BlogMapper.class);
        List<Blog> blogList=mapper.queryList("%标题%","id");
        for(Blog blog:blogList){
            System.out.println(blog);
        }
    }

    @Test
    public void selectBlogWithAuthorDemo() {
        SqlSession sqlSession=getSqlSessionFactory().openSession();

        //接口式编程，动态代理实现
        BlogMapper mapper=sqlSession.getMapper(BlogMapper.class);
        Blog blog=mapper.queryBlogWithAuthorById(1);
        System.out.println(blog);
        System.out.println(blog.getAuthor());
    }

    @Test
    public void selectBlogWithAuthor2Demo() {
        SqlSession sqlSession=getSqlSessionFactory().openSession();

        //接口式编程，动态代理实现
        BlogMapper mapper=sqlSession.getMapper(BlogMapper.class);
        Blog blog=mapper.queryBlogWithAuthorById2(1);
        System.out.println(blog);
        System.out.println(blog.getAuthor());
    }

    @Test
    public void selecAuthorWithBlogDemo() {
        SqlSession sqlSession=getSqlSessionFactory().openSession();

        //接口式编程，动态代理实现
        AuthorMapper mapper=sqlSession.getMapper(AuthorMapper.class);
        Author author=mapper.queryAuthorWithBlogById(1);
        System.out.println(author);
        System.out.println(author.getBlogs());
    }

    @Test
    public void selecBlogsDynamicSqlDemo() {
        SqlSession sqlSession=getSqlSessionFactory().openSession();

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
        SqlSession sqlSession=getSqlSessionFactory().openSession();

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
        SqlSession sqlSession=getSqlSessionFactory().openSession();

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

    /**
     * 分页插件
     */
    @Test
    public void pageHelperTest(){
        SqlSession sqlSession=getSqlSessionFactory().openSession();
        BlogMapper mapper=sqlSession.getMapper(BlogMapper.class);
        Page<Object> pageBlog=PageHelper.startPage(1,2);
        List<Blog> allBlogs=mapper.queryAllBlog();
        System.out.println(allBlogs);

        System.out.println(pageBlog.getPageNum());
        System.out.println(pageBlog.getTotal());
        sqlSession.close();

    }

    @Test
    public void batchInsert(){
        SqlSession sqlSession=getSqlSessionFactory().openSession(ExecutorType.BATCH);
        BlogMapper mapper=sqlSession.getMapper(BlogMapper.class);

        long startTime=System.currentTimeMillis();
        for(int i=1001;i<2000;i++){
            mapper.insertBlog(new Blog(i,"title"+i,"content"+i));
        }

        sqlSession.commit();
        sqlSession.close();
        long endTime=System.currentTimeMillis();

        System.out.println("执行的时间："+(endTime-startTime)+"毫秒");
    }

}
