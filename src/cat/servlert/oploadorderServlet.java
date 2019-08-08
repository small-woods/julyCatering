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

@WebServlet("/oploadorder")
public class oploadorderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int currentpage = Integer.parseInt(request.getParameter("currentPage"));
        String ordetype = request.getParameter("ordetype");
        System.out.println("currentpage-------------: "+currentpage);
        System.out.println("ordetype----------------: "+ordetype);
        JSONArray res = null;
        res = new OrdeSevice().jsonselectOrderGroup(currentpage,ordetype);
        System.out.println("oploadorder-->res: "+res);
        out.println(res);
    }
}
