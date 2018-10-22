package cn.np.mybatis.mapper;

import cn.np.mybatis.bean.Department;

/**
 * @author np
 * @date 2018/10/21
 */
public interface DepartmentMapper {

    Department queryDepartEmp(String name);
}
