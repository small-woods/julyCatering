package cat.servlert;

import cat.service.OrdeSevice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/typeorderspagecount")
public class typeorderspagecountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String type = request.getParameter("ordeType");
        System.out.println("type： "+type);
        String ordeTypeNum = new OrdeSevice().ordeTypeNum(type);
        System.out.println("ordeTypeNum: "+ordeTypeNum);
        out.println(ordeTypeNum);
    }
}
