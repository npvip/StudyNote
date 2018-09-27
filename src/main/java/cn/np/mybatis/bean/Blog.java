package cn.np.mybatis.bean;


import java.io.Serializable;

/**
 * @author np
 * @date 2018/9/5
 */
public class Blog implements Serializable {

    private int id;
    private String title;
    private String content;

    private Author author;

    public Blog() {
    }

    public Blog(int id,String title, String content) {
        this.id=id;
        this.title = title;
        this.content = content;
        this.author=new Author(5,"张三","三国猛将");

    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                '}';
    }
}
