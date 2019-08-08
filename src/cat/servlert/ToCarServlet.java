package cat.servlert;

import cat.bean.FoodDetail;
import cat.bean.Orde;
import cat.service.OrdeSevice;
import cat.service.TableService;
import cat.service.ToCarService;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ToCar")
public class ToCarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        System.out.println("type:"+type);
        String orderno ;
        String foodname ;
        String num ;

        /*
         *如果 type =1 表示先往订单详情表中添加订单号，美食名称，美食数量（美食数量第一次默认为1）
         * 如果type =2 询相关美食信息，显示到订单表里面
         * 如果type =3 为在购物车表里面为加减数量添加事件，修改数据库内容信息
         * 如果type =4 表示删除购物车该行的内容
         */

        /*
         *type =1时
         *   type:'1',orderno:orderno,foodname:foodname
         */
        if(type.equals("1")){
            orderno = request.getParameter("orderno");
            foodname= request.getParameter("foodname").trim();
            num = "1";
            System.out.println("orderno:"+orderno+"foodname:"+foodname);
            ToCarService toCarService = new ToCarService();
            if(toCarService.isinsert(orderno, foodname)){
                if(toCarService.insertCarInfo(orderno, foodname, num)){
                    out.println("1");
                }else {
                    out.println("0");
                }
            }
            else {
                out.println("2");
            }
        }

        /*
         *type =2时
         */
        if(type.equals("2")){
             orderno =request.getParameter("orderno");
            System.out.println("orderno: "+orderno);
            JSONArray res = null;

            res = new ToCarService().jsonSelctCar(orderno);
            System.out.println("type =2--->res: "+res);
            out.println(res);
        }

        if(type.equals("4")){
            foodname = request.getParameter("foodname");
            if(new ToCarService().delCarrow(foodname)){
                out.println("true");
            }
        }

        if(type.equals("3")){
            String op =request.getParameter("op");
            foodname = request.getParameter("foodname");
            if(op.equals("1")){
                if(new ToCarService().updatacarnumdel("xx",foodname,"cc")){
                    out.println("delok");
                }
            }
            if(op.equals("0")){
                if(new ToCarService().updatacarnumadd("xx",foodname,"cc")){
                    out.println("addok");
                }
            }

        }

        if(type.equals("5")){
            String money = request.getParameter("money");
            String tableno = request.getParameter("tableno");
            orderno = request.getParameter("orderno");
            System.out.println("money:"+money);
            // public boolean updataorde(String orderno, String tableno, String orderpeople,String ordertime,String orderallmoney,String ordertype)
           if(new OrdeSevice().updataorde(orderno,"xx","xx","xx",money,"1")){
               if(new TableService().tableChangeso(tableno)){
                   out.println("1");
               }

            }
           else {
               out.println("0");
           }
        }
    }
}
