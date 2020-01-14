package cn.p.util;


import cn.p.comm.Excel;
import cn.p.comm.ExcleHeard;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ExcelUtils {
    //关于excel 了解几个对象
    public static void excelutil(List list, HttpServletResponse response){
        /*首先处理标题*/
        Object o = list.get(0);//得到要下载的对象

        Class aClass = o.getClass();//利用反射得到类的对象

        //获取标题名 以及标题上面的注解
        ExcleHeard annotation = (ExcleHeard) aClass.getAnnotation(ExcleHeard.class);
        //获取标题名
        String title = annotation.title();

        XSSFWorkbook book = new XSSFWorkbook();

        XSSFSheet sheet = book.createSheet(title);

        XSSFRow row = sheet.createRow(0);

        //处理列头
        //得到类的所以属性
        Field[] fields = aClass.getDeclaredFields();

        int cellNom=0;

        for (int i = 0; i <fields.length ; i++) {
            //具体每一个属性
            Field field=fields[i];

            //判断此属性是否要导出来的属性
            Excel annotation1 = field.getAnnotation(Excel.class);

            if (annotation1!=null){
              String rownumname=annotation1.name();
                XSSFCell cell = row.createCell(cellNom);
                cell.setCellValue(rownumname);
                cellNom++;
            }

        }
        //处理数据
        for (int i = 0; i <list.size() ; i++) {
            //获取具体数据
            Object o1 = list.get(i);
            //创建行 +1
            XSSFRow row1 = sheet.createRow(i + 1);
            int cells=0;
            for (int j = 0; j <fields.length ; j++) {
                //具体每一个属性
                Field fields1=fields[j];
                boolean annotationPresent = fields1.isAnnotationPresent(Excel.class);
                if (annotationPresent==true){
                    XSSFCell cell = row1.createCell(cells);
                    //获取属相值 在反射中 对类和对象来说

                    try {
                        fields1.setAccessible(true);
                        Object o2 = fields1.get(o1);
                        if (o2!=null){
                            Class<?> type = fields1.getType();
                            if (type== Date.class){
                                //g格式化日期
                                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                                Date date=(Date)o2;
                                String format = sdf.format(date);
                                cell.setCellValue(format);
                            }else {
                                cell.setCellValue(o2.toString());
                            }
                        }else {
                            cell.setCellValue("");
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    cells++;

                }

            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=\""+ UUID.randomUUID().toString()+".xlsx\"");

        ServletOutputStream outputStream=null;

        try {
            outputStream = response.getOutputStream();
            book.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
