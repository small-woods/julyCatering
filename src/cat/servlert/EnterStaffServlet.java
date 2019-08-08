package cat.servlert;

import cat.service.OrdeSevice;
import cat.service.TableService;
import cat.utils.CurrentDateUtils;
import cat.utils.OrderCodeFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EnterStaff")
public class EnterStaffServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String tableno = request.getParameter("tableno");
        String tablenum = request.getParameter("tablenum");
        String staffno = request.getParameter("staffno");
        String orderCode;
        String ordertime;
        System.out.println("tableno: "+tableno+" tablenum: "+tablenum+" staffno: "+staffno);
//        String orderCode = new TableService().tableChange(tableno);
//        orderCode = new OrderCodeFactory().getOrderIdByTime();

        //如果餐桌状态为0，说明无人使用，返回true到前台，并且将该餐桌状态改为1，同时，生成一个订单编号，发送给前台+将订单号，餐桌编号，下单时间，结账状态为0插入订单表
        if(!new TableService().tableable(tableno)) {
            if(new TableService().tableChange(tableno) !=0){
                orderCode = new OrderCodeFactory().getOrderIdByTime();
                ordertime = new CurrentDateUtils().currentDate();
                if(new OrdeSevice().insertorde(orderCode,tableno, tablenum,ordertime,"0","0")){
                    System.out.println("orderCode:  "+orderCode);
                    out.println("1&"+orderCode);
                }
            }
            System.out.println("更改之后的餐桌的状态: "+new TableService().tableable(tableno));
        }
        //餐桌状态为1，说明有顾客在使用，放回false到前台，并从数据库查询该用户的流水账号，台号，使用人数返回给前台
        else{
                String str = new OrdeSevice().orderpeopels(tableno);
            out.println("0&"+str);

        }

    }
}
