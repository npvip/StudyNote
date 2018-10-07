package cn.np.common;

/**
 * @author np
 * @date 2018/10/7
 */
public class CloneDemo {


    public static void main(String[] args) throws CloneNotSupportedException {
        // 普通赋值语句
        Dog dog1 = new Dog();
        dog1.setName("jack");
        Dog dog2 = new Dog();
        dog2 = dog1;

        System.out.println(dog1 == dog2);// true
        // dog1和dog2指向同一块堆内存
        dog1.setName("rose");

        System.out.println(dog1.getName());
        System.out.println(dog2.getName());

        // 浅克隆
        Cat cat1 = new Cat();
        cat1.setName("cafe");
        cat1.setAge(3);
        cat1.setDog(dog1);

        Cat cat2 = (Cat)cat1.clone();

        System.out.println("cat2:" + cat2);
        System.out.println(cat1 == cat2); // false
        // 修改克隆对象的基本数据类型的属性(对原对象对应属性没有影响)
        cat2.setAge(5);
        cat2.setName("jay");
        System.out.println("cat2:" + cat2);
        System.out.println("cat1:" + cat1);
        // 修改克隆对象的引用类型的属性(原对象的对应属性也跟随着修改)
        cat2.getDog().setName("kcaj");
        System.out.println("cat2:" + cat2);
        System.out.println("cat1:" + cat1);

    }
}

class Dog {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Cat implements Cloneable {
    private String name;
    private int age;
    private Dog dog;

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

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("cat clone()");
        return super.clone();
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", dog=" + dog +
                '}';
    }
}