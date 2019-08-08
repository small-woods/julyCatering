package cat.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wcfb on 2019/4/3
 * 读取excel表格的工具类
 * 导入的jar包
 * poi-3.8-beta3-20110606.jar
 * poi-ooxml-3.8-beta3-20110606.jar
 * poi-examples-3.8-beta3-20110606.jar
 * poi-excelant-3.8-beta3-20110606.jar
 * poi-ooxml-schemas-3.8-beta3-20110606.jar
 * poi-scratchpad-3.8-beta3-20110606.jar
 * xmlbeans-2.3.0.jar
 * dom4j-1.6.1.jar
 * jar包官网下载地址：http://poi.apache.org/download.html
 *下载poi-bin-3.8-beta3-20110606.zipp
 */
public class ReadExcelUtils {

    int totalRows = 0;
    int totalCells = 0;
    //保存所有excel的数据
    List<List<List<String>>> dataLists = new ArrayList<>();

    public ReadExcelUtils() {
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public void setTotalCells(int totalCells) {
        this.totalCells = totalCells;
    }

    public boolean validateExcel(String filePath){
        System.out.println("utils.ReadExecl.validateExcel:validateExcel.filePath:"+filePath);
        //检查文件格式
        if (filePath == null
                || !(WDWUtil.isXls(filePath) || WDWUtil.isXlsx(filePath))){
            System.out.println("utils.ReadExecl.validateExcel:文件不存在");
            return false;
        }
        //检查文件是否存在
        File file = new File(filePath);
        if (file == null || !file.exists()){
            System.out.println("utils.ReadExecl.validateExcel:文件不存在");
            return false;
        }
        return true;
    }

    public List<List<List<String>>> read(String filePath){
        InputStream inputStream = null;
        try {

            System.out.println("utils.ReadExecl.read:检查文件的合法性...");
            //检查文件的合法性
            if (!validateExcel(filePath)){
                return null;
            }
            //判断文件类型 .xls or .xlsx
            boolean isXls = true;
            if (WDWUtil.isXlsx(filePath)){
                isXls = false;
            }
            //调用本类的读取方法
            File file = new File(filePath);
            inputStream = new FileInputStream(file);
            dataLists = read(inputStream, isXls);
            inputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (Exception e){
                    inputStream = null;
                    e.printStackTrace();
                }
            }
        }
        return dataLists;
    }

    public List<List<List<String>>> read(InputStream inputStream, boolean isXls){
        try{
            Workbook workbook = null;
            if (isXls){
                workbook = new HSSFWorkbook(inputStream);
            }
            else {
                workbook = new XSSFWorkbook(inputStream);
            }
            int count = workbook.getNumberOfSheets();
            for (int i = 0; i < count; i++) {
                dataLists.add(read(workbook,i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataLists;
    }

    /**
     * 读取excel
     * @param workbook
     * @param index 不同的sheet
     * @return
     */
    private List<List<String>> read(Workbook workbook, int index){
        //这个sheet的内容
        List<List<String>> dataList = new ArrayList<>();
        //得到第一个sheet
        Sheet sheet = workbook.getSheetAt(index);
        //得到excel行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        //得到列数
        if (this.totalRows >= 1 && sheet.getRow(0) != null){
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        //循环行数
        for (int i = 0; i < this.totalRows; i++) {
            Row row = sheet.getRow(i);
            if (row == null){
                continue;
            }
            List<String> rowList = new ArrayList<>();
            for (int c = 0; c < this.getTotalCells(); c++) {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell) {
                    CellType cellType = cell.getCellType();
                    switch (cellType){
                        case STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            cellValue = String.valueOf(cell.getNumericCellValue());
                            break;
                        case FORMULA:
                            cellValue = cell.getCellFormula();
                            break;
                        case BLANK:
                            cellValue = cell.getStringCellValue();
                            break;
                        default:
                            cellValue = cell.getStringCellValue();
                            break;
                    }
                    rowList.add(cellValue);
                }
            }
            /** 保存第r行的第c列 */
            dataList.add(rowList);
        }
        return dataList;
    }
    /**
     * 测试类
     * @param args
     */
    public static void main(String[] args){
        List<List<List<String>>> dataList = new ReadExcelUtils().read("D:\\临时文件\\测试.xlsx");
        System.out.println("ReadExcel:main:");
        String str = "";
        for (int i = 0; i < dataList.size(); i++) {
            for (int i1 = 1; i1 < dataList.get(i).size(); i1++) {
                str+="[";
                str += dataList.get(i).get(i1).get(0);
                str+=",";
                str+=dataList.get(i).get(i1).get(1);
                    str+="]\n";
                System.out.print(dataList.get(i).get(i1) + " ");
            }
           System.out.println();
        }System.out.println("str: "+str);
    }
}