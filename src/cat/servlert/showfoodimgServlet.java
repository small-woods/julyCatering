package cat.servlert;

import cat.service.FoodSevice;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/showfoodimg")
public class showfoodimgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int currentpage = Integer.parseInt(request.getParameter("currentPage"));
        String foodtype = request.getParameter("foodType");
        System.out.println("currentpage: "+currentpage);
        System.out.println("foodtype: "+foodtype);
        JSONArray res = null;
        res = new FoodSevice().jsonselectFoodGroup(currentpage,foodtype);
        System.out.println("jsonselectFoodGroup-->res: "+res);
        out.println(res);
    }
}
