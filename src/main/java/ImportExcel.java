
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImportExcel {
    public static void main(String[] args) {

        JSONArray jsonArray = getJsonData();
        List<JSONObject> list = new ArrayList<>();
        getTree(jsonArray,list);
        System.out.println();
        writeToExcel(list);
    }

    @SneakyThrows
    private static void writeToExcel(List<JSONObject> list) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("课程表");
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell00 = row.createCell(0);
            cell00.setCellValue("name");
            HSSFCell cell01 = row.createCell(1);
            cell01.setCellValue("path");
            HSSFCell cell02 = row.createCell(2);
            cell02.setCellValue("component");

            for (int i = 0; i < list.size(); i++) {
                HSSFRow rowHSSF = sheet.createRow(i+1);
                for (int cellNo = 0; cellNo <= 2; cellNo++) {
                    HSSFCell cell = rowHSSF.createCell(cellNo);
                    switch (cellNo){
                        case 0:
                            System.out.println(0);
                            cell.setCellValue(list.get(i).getString("name"));
                            break;
                        case 1:
                            System.out.println(1);
                            cell.setCellValue(list.get(i).getString("path"));
                            break;
                        case 2:
                            System.out.println(2);
                            cell.setCellValue(list.get(i).getString("component"));
                            break;
                    }
                }
            }


            FileOutputStream out = new FileOutputStream("D:/课程表2.xls");
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void getTree(JSONArray jsonArray,List<JSONObject> list) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            list.add(jsonObject);
            if (jsonObject.getJSONArray("children") != null){
                getTree(jsonObject.getJSONArray("children"),list);
            }
        }
    }

    /**
     * 读取json数据并解析
     * @return
     * @throws IOException
     */
    public static JSONArray getJsonData(){
        return  JSONObject.parseObject(Test.str.trim()).getJSONArray("data");
    }
}
