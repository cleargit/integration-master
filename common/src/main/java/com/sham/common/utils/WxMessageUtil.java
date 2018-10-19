package com.sham.common.utils;

public class WxMessageUtil {

//    public static Map<String, String> parseXml() {
//        HttpServletRequest request = ComUtil.getRequest();
//        Map<String, String> map = new HashMap<>();
//        InputStream inputStream=null;
//        try {
//            SAXReader reader = new SAXReader();
//            inputStream = request.getInputStream();
//            Document document = reader.read(inputStream);
//            //获取根节点
//            Element root = document.getRootElement();
//            //获取所有根下的子节点
//            List<Element> elementList = root.elements();
//            for (Iterator iterator=elementList.iterator();iterator.hasNext();){
//                Element e= (Element) iterator.next();
//                map.put(e.getName(),e.getText());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if (inputStream!=null){
//                    inputStream.close();
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//        return map;
//    }
}
