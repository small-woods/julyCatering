package cat.servlert;

import cat.bean.Logined;
import cat.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String url = request.getServletPath();
        System.out.println(url);
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        String password = request.getParameter("password");


        System.out.println("id:"+id);
        System.out.println("type:"+type);
        System.out.println("password:"+password);

        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session=request.getSession();

        //管理员
        if((new LoginService().login(type, id, password))&&type.equals("1")){
            response.getWriter().print("admin,"+id);
            Logined user=new Logined(id);
            session.setAttribute(id,user);
            //普通员工
        }else if((new LoginService().login(type, id, password))&&type.equals("0")){
            response.getWriter().print("staff,"+id);
            Logined user=new Logined(id);
            session.setAttribute(id,user);
        }else{
            response.getWriter().print("error");
        }

    }
}
