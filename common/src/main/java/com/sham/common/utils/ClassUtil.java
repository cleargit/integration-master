package com.sham.common.utils;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {
    public static List<Class<?>> getclass(String packname){
        List<Class<?>> classes=new ArrayList<>();
        String packDirname=packname.replace(".","/");
        Enumeration<URL> dirs;
        try {
            dirs=Thread.currentThread().getContextClassLoader().getResources(packDirname);
            while (dirs.hasMoreElements()){
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)){
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findFileClass(packname,filePath,classes);
                }else if ("jar".equals(protocol)){
                    JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
                    Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()){
                        JarEntry entry=entries.nextElement();
                        String name =entry.getName();
                        if (name.startsWith(packDirname)){
                            int ids=name.lastIndexOf("/");
                            if (ids!=-1){
                                packname=name.substring(0,ids).replace("/",".");
                            }
                            if (name.endsWith("class") && !entry.isDirectory()){
                                String className=name.substring(packname.length()+1,name.length()-6);
                                classes.add(Class.forName(packname+"."+className));
                            }

                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return classes;
    }
    public static void findFileClass(String packname,String filePath,List<Class<?>> classes){
        File dir=new File(filePath);
        File[] files=dir.listFiles();
        for (File file: files) {
            try {
            String className=file.getName().substring(0,file.getName().length()-6);
                classes.add(Class.forName(packname+"."+className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
