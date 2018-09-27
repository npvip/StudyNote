package cn.np.mybatis.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author np
 * @date 2018/9/5
 */
public class Author implements Serializable {
    private int id;
    private String name;
    private String desp;
    private List<Blog> blogs;

    public Author() {
    }

    public Author(int id,String name, String desp) {
        this.id=id;
        this.name = name;
        this.desp = desp;
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

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desp='" + desp + '\'' +
                '}';
    }
}
