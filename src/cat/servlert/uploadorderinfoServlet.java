package cat.servlert;

import cat.service.OrdeSevice;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/uploadorderinfo")
public class uploadorderinfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int currentpage = Integer.parseInt(request.getParameter("currentPage"));
        System.out.println("uploadorderinfo   currentpage: "+currentpage);
        JSONArray res = null;
        res = new OrdeSevice().jsonselectorder(currentpage);
        System.out.println("res: "+res);
        out.println(res);
    }
}
