import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.homejim.mybatis.entity.Student;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.jni.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = "/edit")
public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        String name = req.getParameter("name");
//        if (name == null) {
//            name = "world";
//        }
        //在这里链接数据库，获得值就可以了
        StudentMapperTest s = new StudentMapperTest();
        s.init();

    //解决前端发送请求中文乱码问题
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");



        //POST方法 通过getparmater或者getint拿到前台传递的数据

        //getint()表示获取整形数据类型， getparamater获取字符串类型

        //servelet好像只能使用下列方法获取值
//        BufferedReader br = req.getReader();
//        String str = "";
//        String listString = "";
//        while ((str = br.readLine()) != null) {
//                listString += str;
//}

//        将字符串转为json对象
//        System.out.println(listString );
//        JSONObject userJson = JSONObject.parseObject(listString);
//        Student a = JSON.toJavaObject(userJson,Student.class);

//        int id = req.getIntHeader("id");
//         String name1 = req.getParameter("name");
//        String email1 =   req.getParameter("email");
//        String  phone1 =  req.getParameter("phone");


        //为什么不能直接a.name拿数据？因为是私有数据不能直接访问
//        String name1s = a.getName();
//        String emails = a.getEmail();
//        String phones = a.getPhone();
        //        System.out.println(name1+email1+phone1+a.getName()+"看看是啥");
//        int ids = a.getId();
//        这样子可以通过postman的uncloerd类型传过来，不能从浏览器传过来
        s.updateUserinfo(name,email,phone,1);



//        Student ss1 = s.testSelectList();
//        int ss = ss1.getId();
        //创建json对象



//        System.out.println(ss.getInfo());
//        在浏览器进入 / 这个路劲，这里的数据库查询启动了，接下来返回数据,
//
//      ,尝试从浏览器接收表单数据 比如新增用户并存入数据库。

        //解决跨域
        resp.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        resp.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        resp.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        resp.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        resp.setHeader("Access-Control-Allow-Credentials", "true");


        //以下两行代码解决返回给前端的中文乱码问题

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.write("修改成功");
        pw.flush();

    }
}
