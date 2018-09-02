package cn.np.spring;

/**
 * @author np
 * @date 2018/9/2
 */
public class HelloWordBean {
    private String name;
    private int size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("【HelloWordBean】setName，name："+name);
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        System.out.println("【HelloWordBean】setSize,size:"+size);
        this.size = size;
    }
}
