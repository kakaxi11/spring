import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

        PrintWriter pw = resp.getWriter();
        String resJSON = "[{id:1, name:‘奔驰’},{id:2, name:‘宝马’}]";

        pw.write(resJSON);
        pw.flush();
    }
}
