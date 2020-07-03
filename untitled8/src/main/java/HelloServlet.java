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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        if (name == null) {
            name = "world";
        }
        //在这里链接数据库，获得值就可以了
        StudentMapperTest s = new StudentMapperTest();
        s.init();
        Student ss1 = s.testSelectList();
        int ss = ss1.getId();
//        System.out.println(ss.getInfo());
//        在浏览器进入 / 这个路劲，这里的数据库查询启动了，接下来返回数据,
//
//      ,尝试从浏览器接收表单数据 比如新增用户并存入数据库。

        //以下两行代码解决中文乱码问题
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        pw.write("<h1>Hello, " + ss+ss1.getName() + "!</h1>");
        pw.flush();
    }
}
