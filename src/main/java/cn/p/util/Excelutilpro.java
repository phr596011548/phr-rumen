package cn.p.util;

import cn.p.comm.MyAnnotation;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.getInt;
import static javax.swing.UIManager.getString;
import static javax.swing.UIManager.removeAuxiliaryLookAndFeel;

/**
 * 输出excel文件
 * @paramd data 数据集合
 * @param
 */
public class Excelutilpro {

    public static void outExcelFile(List<?> data, String path) {

        File file = new File(path);

        // 创建workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        // 创建sheet
        Sheet sheet = wb.createSheet("sheel");

        // 创建表头行
        Row row = sheet.createRow(0);

        // 创建单元格样式
        HSSFCellStyle style = wb.createCellStyle();
        // 居中显示
        /*        style.setAlignment(HorizontalAlignment.CENTER);*/

        // 获取实体所有属性
        Field[] fields = data.get(0).getClass().getDeclaredFields();
        // 列索引
        int index = 0;
        // 列名称
        String name = "";
        MyAnnotation myAnnotation;

        // 创建表头
        for (Field f : fields) {
            // 是否是注解
            if (f.isAnnotationPresent(MyAnnotation.class)) {
                // 获取注解
                myAnnotation = f.getAnnotation(MyAnnotation.class);
                // 获取列索引
                index = myAnnotation.columnIndex();
                // 列名称
                name = myAnnotation.columnName();
                // 创建单元格
                creCell(row, index, name, style);
            }
        }
        // 行索引  因为表头已经设置，索引行索引从1开始
        int rowIndex = 1;
        for (Object obj : data) {
            // 创建新行，索引加1,为创建下一行做准备
            row = sheet.createRow(rowIndex++);
            for (Field f : fields) {
                // 设置属性可访问
                f.setAccessible(true);
                // 判断是否是注解
                if (f.isAnnotationPresent(MyAnnotation.class)) {
                    // 获取注解
                    myAnnotation = f.getAnnotation(MyAnnotation.class);
                    // 获取列索引
                    index = myAnnotation.columnIndex();
                    try {
                        // 创建单元格     f.get(obj)从obj对象中获取值设置到单元格中
                        creCell(row, index, String.valueOf(f.get(obj)), style);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        FileOutputStream outputStream = null;


        try {
            outputStream = new FileOutputStream(file);
            wb.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置属性值
     *
     * @param obj  操作对象
     * @param f    对象属性
     * @param cell excel单元格
     */
    private static void setFieldValue(Object obj, Field f, Workbook wookbook, Cell cell) {
        try {
            if (f.getType() == int.class || f.getType() == Integer.class) {
                f.setInt(obj, getInt(cell));
            } else {
                f.set(obj, getString(cell));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 对excel 2003处理
     */
    private static Workbook xls(InputStream is) {
        try {
            // 得到工作簿
            return new HSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对excel 2007处理
     */
    private static Workbook xlsx(InputStream is) {
        try {
            // 得到工作簿
            return new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建单元格
     *
     * @param row
     * @param c
     * @param cellValue
     * @param style
     */
    private static void creCell(Row row, int c, String cellValue, CellStyle style) {
        Cell cell = row.createCell(c);
        cell.setCellValue(cellValue);
        cell.setCellStyle(style);
    }

}

