package cat.servlert;

import cat.dao.FoodDao;
import cat.service.FoodSevice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/typefoodPageCount")
public class typefoodPageCountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String type = request.getParameter("type");
        System.out.println("type: "+type);
        if(type.equals("凉菜")){
            String foodTypeNum = new FoodSevice().foodTypeNum(type);
            System.out.println("foodTypeNum: "+foodTypeNum);
            out.println(foodTypeNum);
        }
        if(type.equals("招牌热菜")){
            String foodTypeNum = new FoodSevice().foodTypeNum(type);
            System.out.println("foodTypeNum: "+foodTypeNum);
            out.println(foodTypeNum);
        }
    }
}
