package cn.np.common;



import java.io.*;

/**
 * @author np
 * @date 2018/10/7
 */
public class SerializableDemo {

    public static void main(String[] args) {
    //    Person person = new Person("jack", 52);
    //    writePerson(person);

        File f = new File(new File("").getAbsolutePath() + File.separator + "person.txt");
        readPerson(f);
    }



    // 将对象写入到文件中
    public static void writePerson(Person person) {
        String absolutePath = new File("").getAbsolutePath();

        File file = new File(absolutePath + File.separator + "person.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);

            oos = new ObjectOutputStream(fos);
            oos.writeObject(person);
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(oos != null) {
                    oos.close();
                }

                if(fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readPerson(File file) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            try {
                Person p = (Person)ois.readObject();
                System.out.println("读取序列化文件：" + p);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis != null) {
                    fis.close();
                }

                if(ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}