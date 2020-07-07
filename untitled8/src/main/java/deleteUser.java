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
import java.io.*;
import java.util.Arrays;
import java.util.List;

//删除用户的接口

@WebServlet(urlPatterns = "/delet")
public class deleteUser extends HttpServlet {
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
//        String name = req.getParameter("name");
//        String email = req.getParameter("email");
//        String phone = req.getParameter("phone");

//        getParameter应该只能拿url后面拼接的值，paramas部分，不能拿post的

        StringBuffer sb = new StringBuffer();
        InputStream is = req.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String s1 = "" ;
        while((s1=br.readLine())!=null){
            sb.append(s1) ;
        }
        String str =sb.toString();

        System.out.println(str);
        //POST方法 通过getinputStream拿到前台传递的数据,好像不能getparamt

        //getint()表示获取整形数据类型， getparamater获取字符串类型

        //servelet好像只能使用下列方法获取值
//        BufferedReader br = req.getReader();
//        String str = "";
//        String listString = "";
//        while ((str = br.readLine()) != null) {
//                listString += str;
//}
//
//        StringBuffer sb = new StringBuffer() ;
//        InputStream is = req.getInputStream();
//        InputStreamReader isr = new InputStreamReader(is);
//        BufferedReader br = new BufferedReader(isr);
//        String s1 = "" ;
//        while((s1=br.readLine())!=null){
//            sb.append(s1) ;
//        }
//        String str =sb.toString();
//
//
//        //将str转换为对象
        var a = JSONObject.parseObject(str);
        a.getInteger("id");
        s.deleteU(a.getIntValue("id"));
//
//
//
//
////        将字符串转为json对象
//
//        System.out.println(a.getString("name"));
////        JSONObject userJson = JSONObject.parseObject(listString);
////        Student a = JSON.toJavaObject(userJson,Student.class);
//
////        int id = req.getIntHeader("id");
////         String name1 = req.getParameter("name");
////        String email1 =   req.getParameter("email");
////        String  phone1 =  req.getParameter("phone");
//
//
//        //为什么不能直接a.name拿数据？因为是私有数据不能直接访问
//        //如果对象的值是string,使用getString取值，如果是int使用getInterger
//        s.updateUserinfo(a.getString("name"),a.getString("email"),a.getString("phone"),a.getIntValue("id"));
//
//
//
////        Student ss1 = s.testSelectList();
////        int ss = ss1.getId();
//        //创建json对象
////        System.out.println(ss.getInfo());
////        在浏览器进入 / 这个路劲，这里的数据库查询启动了，接下来返回数据,
////
////      ,尝试从浏览器接收表单数据 比如新增用户并存入数据库。
//
//        //解决跨域
        resp.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        resp.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        resp.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        resp.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        resp.setHeader("Access-Control-Allow-Credentials", "true");
//        //以下两行代码解决返回给前端的中文乱码问题
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.write("删除成功");
//        pw.flush();

//        修改的总结，1，因为是post请求，所以要通过getinputstream的方式获取数据
//            2，把拿到的json字符串转换为java对象，方便取值
        //3前端再发送请求时要通过json.stringify转为json字符串发送才能成功

    }
}
