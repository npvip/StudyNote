package cn.np.mybatis.mapper;

import cn.np.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author np
 * @date 2018/10/18
 */
public interface EmployeeMapper {
    List<Employee> queryEmp();

    // 增
    void addEmp(Employee employee);

    // 查-单个参数
    Employee queryEmpById(Integer id);

    // 查-多个参数
    Employee queryEmpByNameAge(@Param("name") String name, @Param("age") Integer age);

    // 关联查询-级联属性结果
    Employee queryEmpAndDept(@Param("id") Integer id);
}
