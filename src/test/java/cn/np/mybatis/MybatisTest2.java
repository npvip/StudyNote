package cn.np.mybatis;

import cn.np.mybatis.bean.Employee;
import cn.np.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author np
 * @date 2018/10/18
 */
public class MybatisTest2 {

    public static SqlSessionFactory getSqlSessionFactory()  {
        String resource = "cn/np/mybatis/example/mybatis-config.xml";
        InputStream ins = null;
        try {
            ins = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(ins);
        return sqlSessionFactory;
    }

    @Test
    public void testEmpQuery() {
        SqlSessionFactory sqlSessionFactory =  getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();

        try{
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
            List<Employee> emps = employeeMapper.queryEmp();
            System.out.println(emps);
        }finally {
            session.close();
        }
    }

    @Test
    public void testAddEmp() {
        SqlSessionFactory sqlSessionFactory =  getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
            Employee employee = new Employee("babana", 24, "女", "业务部");
            employeeMapper.addEmp(employee);

            System.out.println(employee.getId());
            session.commit();
        }finally {
            session.close();
        }
    }

    @Test
    public void testQueryEmp() {
        SqlSessionFactory sqlSessionFactory =  getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
            //查-单个参数
            Employee employee = employeeMapper.queryEmpById(1);
            //查-多个参数
            Employee employee1 = employeeMapper.queryEmpByNameAge("章三", 18);

            System.out.println(employee);
            System.out.println(employee1);
            session.commit();
        }finally {
            session.close();
        }
    }
}
