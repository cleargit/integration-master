package com.sham.excel.service;


import org.apache.poi.ss.formula.functions.T;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface ExeclService {
    void exportExecl(String template, OutputStream os, List data, Class clazz, Map<String, String> map);
    void exportExecl(String template,String filePath, List data, Class clazz, Map<String, String> map);
    List<T> readExecl(String filePath,T clazz,Integer readLine,Integer endLine);
}
