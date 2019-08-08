package cat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateUtils {

     public String currentDate(){
         long nowtime = System.currentTimeMillis();
         Date time = new Date();
         time.setTime(nowtime);
         SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         System.out.println("格式化后的时间------->"+format.format(time));
         return format.format(time);
    }

    public static void main(String[] args){
         new CurrentDateUtils().currentDate();
    }

}
