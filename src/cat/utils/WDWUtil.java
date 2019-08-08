package cat.utils;

/**
 * Created by wcfb on 2019/4/3
 * 判断是否为excel
 */
public class WDWUtil {
    public static boolean isXls(String filePath){
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    public static boolean isXlsx(String filePath){
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

}
