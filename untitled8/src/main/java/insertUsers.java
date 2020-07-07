
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
import java.io.*;

//新增用户信息
@WebServlet(urlPatterns = "/adds")
public class insertUsers extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        resp.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        resp.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        resp.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        resp.setHeader("Access-Control-Allow-Credentials", "true");

//        第一步，创建一个StudentMapper类对象，这个对象里有各种增删改查的方法可以调用

//        第二步，根据时post或者get请求，来拿到前台的数据，并做出一些配置，解决中文乱码等,跨域等，
//         并把前台的数据转换为对象或者可用的形式
        StudentMapperTest s2 = new StudentMapperTest();
//
        s2.init();






        StringBuffer sb = new StringBuffer();
        InputStream is = req.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String s1 = "" ;
        while((s1=br.readLine())!=null){
            sb.append(s1) ;
        }
        String str =sb.toString();
        //json字符串转换为java对象
        var user = JSONObject.parseObject(str);
        //第三步，调用SudentMapperTest对象里的新增函数，传入形参

        System.out.println(user.getString("name"));
        s2.insertUser(user.getInteger("id"),user.getString("name"),user.getString("phone"),user.getString("email"),user.getByte("sex"));


        //第四部，返回一些操作成功或者失败的信息状态码等

        //以下两行代码解决返回给前端的中文乱码问题



        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        pw.write("新增成功");
        pw.flush();
    }
}