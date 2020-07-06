import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.homejim.mybatis.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = "/foot")
public class footServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        if (name == null) {
            name = "重庆";
        }

        //ajax和axios这种请求方式会发送跨域，直接请求不会,下面4行解决跨域
        resp.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        resp.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        resp.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        resp.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        resp.setCharacterEncoding("UTF-8");


        //这里如果要返回json第一个数据就改为application/json而不是text/xml，如果要接收表单则为表单
        resp.setContentType("application/json;charset=utf-8");

        //还可以添加更多的状态码，和状态码对应的报错信息。

//        因为返回的说json，所以前端还需要先从json转为字符串，再转为数组，现在能在前端拿到数据并处理了，只是转化为数组时还是有点问题/
        StudentMapperTest s = new StudentMapperTest();
        s.init();
        Student ss1 = s.testSelectList(1);
        Student ss2 = s.testSelectList(2);
//        Student ss3 = s.testSelectList(5);
        Student ss5 = s.testSelectList(5);
//
//        JSONObject object = new JSONObject();
//        //string
//        object.put("string","string");
//        //int
//        object.put("int",2);
//        //boolean
//        object.put("boolean",true);
//        //array
//
//
//        JSONObject object2 = new JSONObject();
//        //string
//        object.put("string","对象2");
//        //int
//        object.put("int",3);
//        //boolean
//        object.put("boolean",false);
//        //array
//
//
//
//
//
//
//        JSONArray ary = new JSONArray();
//         ary.add(object);
//        ary.add(object2);


        //改为数据库里面的数据

        JSONArray jsonArray = new JSONArray();
//        // json对象1
//        JSONObject json1 = new JSONObject();
//        json1.put("name","小王");
//        json1.put("phone","684155684");
//        json1.put("email","1099965786@qq.com");
//        json1.put("sex",1);
//        // json对象2
//
////        如果要把数据从前台获取插入数据库，则这些值应该从请求体中获取到
//        JSONObject json2 = new JSONObject();
//        json2.put("name","小周");
//        json2.put("phone","185464648");
//        json2.put("email","aojew@.qq.com");
//        json1.put("sex",0);


        // 将json对象添加到json数组
        jsonArray.add(ss1);
        jsonArray.add(ss2);
//        jsonArray.add(ss3);
        jsonArray.add(ss5);
//        通过id查询得到的对象，每一个get方法就是该对象的一个键值对，键是get后面的那个单词，必须每个属性都要有对应的get方法，才能把每个属性都一行一行的显示出来。



        PrintWriter pw = resp.getWriter();
//        String resJSON = ss1.getInfo();

        //再把json对象转为字符串返回给前端,前端才能识别。
        pw.write(jsonArray.toString());
        pw.flush();
    }
}
