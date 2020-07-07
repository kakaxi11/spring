import com.homejim.mybatis.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class StudentMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    //返回Student对象类型的数组
    public  List<Student> testSelectList(int index) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            //泛型数据数组 Student类型 获得全部数据的接口
            System.out.println("查询成功");
            List<Student> students = sqlSession.selectList("selectAll");

            //index为获取指定条数据，students.size()为获取全部数据
            for (int i = 0; i < index; i++) {

                //前面一个是List容器数组的方法，后面一个是对象自带的方法，用于获得该对象的名字。

                System.out.println(students.get(i).getInfo());
            }


            return students;
//            System.out.println("下面打印单个ID查询数据");
            //根据id查询单条记录的接口
//            Student a = sqlSession.selectOne("selectByPrimaryKey", index);
////            int aa = a.getId();
//            return a;
//            return  aa;
//            System.out.println(a.getInfo());
//        sqlSession.selectOne("updateByPrimaryKey",)
//            System.out.println(a.getInfo());
//sqlSession封装了多种sql增删改查方法,修改数据的接口
//            Student a = new Student();
//            a.setEmail("1099965awwaeaw@qq.com");
//            a.setName("yes");
//            a.setPhone("18591566521");
//            a.setStudentId(4);
//            int result = sqlSession.update("updateByPrimaryKey",a);
//            System.out.println("操作成功");
//            sqlSession.commit();


//            添加和修改方法后面要加commit才会成功

//            根据id删除某条记录  .,可以从前端获取数据
//            int result1 = sqlSession.delete("deleteByPrimaryKey",6);


//            sqlSession.commit();
//            Student b = new Student();
//            b.setStudentId(10);
//            b.setName("卡卡");
//            b.setPhone("15684446448");
//            b.setEmail("5561@ghmail.com");
////插入数据时如果只想插入指定几列的数据必须先声明指定列,在mapper文件里的数据也有所区别，只需在values里写(#{id},#{name})这种形式即可
//            int result2 = sqlSession.insert("insertObj",b);
//                sqlSession.commit();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
//            if (sqlSession != null) {
//                sqlSession.close();
//            }
//            //没有返回类型又必须返回时可以选择null返回
//            String ww = "hellosmotot";
//            return ww;
        }
    }

    @Test
    public void updateUserinfo(String name, String email, String phone, int id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();

            Student a = new Student();
            a.setEmail(email);
            a.setName(name);
            a.setPhone(phone);
            a.setStudentId(id);


            int result = sqlSession.update("updateByPrimaryKey", a);
            System.out.println("操作成功" + email + name + phone + id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }


    @Test
    public void insertUser(int id,String name, String phone, String email,int sex) {
        SqlSession sqlSession = null;
        try {
            //记得打开sqlssionFactory才能进行调用mybatis的 jdbc操作
            sqlSession = sqlSessionFactory.openSession();
            Student b = new Student();
            b.setStudentId(id);
            b.setName(name);
            b.setPhone(phone);
            b.setEmail(email);
            b.setSex(sex);
//插入数据时如果只想插入指定几列的数据必须先声明指定列,在mapper文件里的数据也有所区别，只需在values里写(#{id},#{name})这种形式即可
            int result2 = sqlSession.insert("insertObj", b);
            System.out.println("新增成功" + email + name + phone);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }

    @Test
    public void deleteU(int id) {
        SqlSession sqlSession = null;
        try {
            //记得打开sqlssionFactory才能进行调用mybatis的 jdbc操作
            sqlSession = sqlSessionFactory.openSession();
//插入数据时如果只想插入指定几列的数据必须先声明指定列,在mapper文件里的数据也有所区别，只需在values里写(#{id},#{name})这种形式即可
            int result2 = sqlSession.delete("deleteByPrimaryKey", id);
            System.out.println("删除成功");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }
}

