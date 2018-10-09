package com.sham.common.utils;

import com.sham.common.dto.AjaxResult;
import java.io.*;
import java.util.*;

/*
 *  
 * 
 * @author sham
 * @date 2018/10/9 9:22
 */

public class CsvUtil {

    public static byte[] exportCsv(LinkedHashMap<String,String> header, List<LinkedHashMap<String, Object>>data){
        ByteArrayOutputStream baos=null;
        BufferedWriter bw=null;
        try {
            baos=new ByteArrayOutputStream();
            bw=new BufferedWriter(new OutputStreamWriter(baos,"gb2312"));
            Map.Entry propertyEntry=null;
            for (Iterator<Map.Entry<String, String>> iterator = header.entrySet().iterator(); iterator.hasNext();) {
                propertyEntry=iterator.next();
                bw.write("\""+propertyEntry.getValue().toString()+"\"");
                if (iterator.hasNext()){
                    bw.write(",");
                }
            }
            bw.newLine();
            LinkedHashMap row = null;
            for (Iterator<LinkedHashMap<String, Object>> iterator = data.iterator(); iterator.hasNext(); ) {
                row = iterator.next();
                for (Iterator<Map.Entry> propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext(); ) {
                    propertyEntry = propertyIterator.next();
                    bw.write("\"" + propertyEntry.getValue().toString() + "\"");
                    if (propertyIterator.hasNext()) {
                        bw.write(",");
                    }
                }
                if (iterator.hasNext()) {
                    bw.newLine();
                }
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (baos!=null){
                    baos.close();
                }
                if (bw!=null){
                    bw.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return baos.toByteArray();
    }

    public static byte[] exportCsv(Map<Integer,String> header, List<Map<Integer, Object>> datalist, List<Integer> sort,boolean append){
        ByteArrayOutputStream baos=null;
        BufferedWriter bw=null;
        try {
            baos = new ByteArrayOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(baos, "gb2312"));
            if (!append) {
                for (Iterator<Integer> iterator = sort.iterator(); iterator.hasNext(); ) {
                    bw.write("\"" + header.get(iterator.next()) + "\"");
                    if (iterator.hasNext()) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
            Map row = null;
            for (Iterator<Map<Integer, Object>> data = datalist.iterator(); data.hasNext(); ) {
                row=data.next();
                for (Iterator<Integer> iterator = sort.iterator(); iterator.hasNext();) {
                    bw.write("\""+row.get(iterator.next())+"\"");
                    if (iterator.hasNext()){
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (baos!=null){
                    baos.close();
                }
                if (bw!=null){
                    bw.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return baos.toByteArray();
    }
    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream=new FileOutputStream("demo.Csv");
            LinkedHashMap<String,String> header=new LinkedHashMap<>();
            header.put("1","name");
            header.put("2","age");
            List<AjaxResult> list=new ArrayList<>();
            list.add(new AjaxResult("kkkkkk"));
            list.add(new AjaxResult("shamer"));
            List<LinkedHashMap<String,Object>> data=new ArrayList<>();
            for (AjaxResult info:list) {
                LinkedHashMap linkedHashMap=new LinkedHashMap();
                linkedHashMap.put("1",info.getCode());
                linkedHashMap.put("2",info.getMsg());
                data.add(linkedHashMap);
            }
            fileOutputStream.write(exportCsv(header,data));
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
