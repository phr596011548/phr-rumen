package cn.p.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
    关于excel 的生成和解析
    工具类  通用
 */
public class ExcelUtils {

    private static  final Logger log= LoggerFactory.getLogger(ExcelUtils.class);
    /*
        根据传入的数据 生成XSSFWorkbook
        studnet     title  序号,姓名,年龄       fileds  id,name,age
     */
    public static void downExcel(List data, List<String> titles, List<String> fileds, HttpServletResponse response) throws IOException {
        //创建workbook对象
        XSSFWorkbook wb=new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        //创建表头  相当于列数固定了
        XSSFRow rowTitle = sheet.createRow(0);
        for (int i = 0; i <titles.size() ; i++) {
            //设置表头信息
            XSSFCell cell = rowTitle.createCell(i);
            cell.setCellValue(titles.get(i));
        }
        //将数据写入wb
        for (int i = 0; i < data.size(); i++) {
            Object obj=data.get(i);//获取具体的数据对象
            //创建row
            XSSFRow row = sheet.createRow(i+1);

            for (int j = 0; j <fileds.size(); j++) {

                //创建列
                XSSFCell cell = row.createCell(j);
                //获取列相关的数据
                String filed = fileds.get(j);
                //反射
                //获取javabean的类对象
                Class objClass = obj.getClass();
                try {
                    //获取javabean的属性对象
                    Field fildClass = objClass.getDeclaredField(filed);
                    //设置暴力访问
                    fildClass.setAccessible(true);
                    Object filedValue = fildClass.get(obj);
                    if(filedValue!=null){//判断是否为空
                        //判断是否为date  所有数据 都按字符串处理导出
                        Class filedValueClass = filedValue.getClass();
                        if(Date.class==filedValueClass){
                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                            String format = sdf.format(filedValue);
                            cell.setCellValue(format);
                        }else{
                            cell.setCellValue(filedValue+"");
                        }
                    }else{
                        cell.setCellValue("");
                    }


                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    log.error("解析excel的属性 异常："+e.getMessage());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    log.error("取属性值 异常："+e.getMessage());
                }
                //向列里写数据

            }
        }

//下载文件
        //设置编码
        response.setCharacterEncoding("utf-8");
        //设置响应数据类型
        response.setContentType("application/octet-stream");//设置响应类型 告诉浏览器输出内容为流
        //设置响应文件名
        response.setHeader("Content-disposition", "attachment;filename="+ UUID.randomUUID().toString()+".xlsx");
        //获取响应流
        ServletOutputStream os = response.getOutputStream();
        //将workbook的内容 写入输出流中
        wb.write(os);
        os.close();
    }
}
