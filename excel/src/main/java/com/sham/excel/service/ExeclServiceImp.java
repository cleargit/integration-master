package com.sham.excel.service;

import com.sham.common.annotation.ExportExecl;
import com.sham.excel.ExcelTemplate;
import com.sham.excel.ExeclHeader;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class ExeclServiceImp implements ExeclService {
    @Override
    public void exportExecl(String template, OutputStream os, List data, Class clazz, Map<String, String> map) {
        try {
            ExcelTemplate et = this.handlerTemplate(template, data, clazz);
            et.replaceData(map);
            et.writeOs(os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportExecl(String template, String filePath, List data, Class clazz, Map<String, String> map) {
        ExcelTemplate et = this.handlerTemplate(template, data, clazz);
        et.replaceData(map);
        et.writeFile(filePath);
    }

    @Override
    public List<T> readExecl(String filePath, T clazz, Integer readLine, Integer endLine) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filePath));
            return this.handlerRead(wb, clazz, readLine, endLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<T> handlerRead(HSSFWorkbook wb, T t, Integer readLine, Integer endLine) {
        List<T> result = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);
        Row titleRow = sheet.getRow(readLine);
        Map<Integer, String> map = this.getRowFiledMap(titleRow,t.getClass());
        for (int i = readLine+1; i <endLine+1 ; i++) {
          Row row=sheet.getRow(i);
            try {
                T info=t.getClass().newInstance();
                Iterator cor=row.iterator();
                while (cor.hasNext()){
                    Cell cell= (Cell) cor.next();
                    String val=cell.getStringCellValue().trim();
                    BeanUtils.copyProperty(info,map.get(cell.getColumnIndex()),val);
                }
                result.add(info);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }



        return result;
    }

    public Map<Integer, String> getRowFiledMap(Row row, Class clazz) {
        Map<Integer, String> map = new HashMap<>();
        List<ExeclHeader> headers = this.getheader(clazz);
        Iterator ror = row.iterator();
        while (ror.hasNext()) {
            Cell cell = (Cell) ror.next();
            Iterator hor = headers.iterator();
            while (hor.hasNext()) {
                ExeclHeader header = (ExeclHeader) hor.next();
                String str=cell.getStringCellValue().trim();
                if (str.equals(header.getTitle())){
                    map.put(cell.getColumnIndex(), header.getMethodName().substring(3).toLowerCase());
                }

            }
        }
        return map;
    }

    private ExcelTemplate handlerTemplate(String template, List data, Class clazz) {
        ExcelTemplate et = ExcelTemplate.getInstance();
        et.readTemplate(template);
        List<ExeclHeader> headers = this.getheader(clazz);
        List<Map<String, Object>> dataList = this.getDataList(headers, data);
        Iterator dor = data.iterator();
        while (dor.hasNext()) {
            Object obj = dor.next();
            et.createNewRow();
            Iterator hor = headers.iterator();
            while (hor.hasNext()) {
                try {
                    ExeclHeader execlHeader = (ExeclHeader) hor.next();
                    String val = BeanUtils.getProperty(obj, this.getMethodName(execlHeader));
                    et.createCell(val);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

            }
        }
        et.setHeader(headers);
        return et;
    }

    private List<Map<String, Object>> getDataList(List<ExeclHeader> headers, List data) {
        List result = new ArrayList();
        Iterator iterator = data.iterator();
        while (iterator.hasNext()) {
            Object info = iterator.next();
            Iterator<ExeclHeader> hi = headers.iterator();
            Map<String, Object> map = new HashMap<>();
            while (hi.hasNext()) {
                try {
                    ExeclHeader header = hi.next();
                    Object val = BeanUtils.getProperty(info, this.getMethodName(header));
                    map.put(header.getMethodName(), val);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            result.add(map);
        }
        return result;
    }

    private String getMethodName(ExeclHeader header) {
        String name = header.getMethodName().substring(3);
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }


    private List<ExeclHeader> getheader(Class clazz) {
        Map<Integer, ExeclHeader> map = new HashMap<>();
        List<ExeclHeader> list = new ArrayList<>();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get") && method.isAnnotationPresent(ExportExecl.class)) {
                ExportExecl an = method.getAnnotation(ExportExecl.class);
                map.put(an.order(), new ExeclHeader(an.title(), an.order(), an.witdh(), method.getName()));
            }
        }
        Integer[] sort = new Integer[map.size()];
        sort = map.keySet().toArray(sort);
        Arrays.sort(sort);
        for (Integer i : sort) {
            list.add(map.get(i));
        }
        return list;
    }

    private void setData(List data, Class clazz) {
        Map<Integer, ExeclHeader> map = new HashMap<>();
        List<ExeclHeader> list = new ArrayList<>();
        List<Integer> sort;
        List<Map<Integer, Object>> datalist = new ArrayList();
        for (Object obj : data) {
            Method[] methods = clazz.getMethods();
            Map<Integer, Object> datamap = new HashMap<>();
            for (Method method : methods) {
                try {
                    ExportExecl an = method.getAnnotation(ExportExecl.class);
                    map.put(an.order(), new ExeclHeader(an.title(), an.order(), an.witdh()));
                    Object val = method.invoke(obj, null);
                    datamap.put(an.order(), val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Integer[] arr = new Integer[map.size()];
        arr = map.keySet().toArray(arr);
        sort = Arrays.asList(arr);
        Collections.sort(sort);
    }

    private void setData(Map<Integer, String> header, List<Map<Integer, Object>> datalist, List<Integer> sort) {


    }
}
