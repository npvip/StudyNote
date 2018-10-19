package cn.np.mybatis.bean;

/**
 * @author np
 * @date 2018/10/18
 */
public class Employee {
    private int id;
    private String name;
    private int age;
    private String sex;
    private String department;

    public Employee() {
    }

    public Employee(String name, int age, String sex, String department) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
