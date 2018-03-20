package com.yz.v1;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class okHttp {
    public static void okIO(String fileName,String imgName,String imgurl){
        InputStream inputStream = null;
        byte[] buf=new byte[1024];
        int len = 0;
        try {
            URL url = new URL(imgurl);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
//            String filename = "E:\\temp\\images\\"+fileName;
//            System.out.println(filename);
            File file = new File("E:\\temp\\images\\"+fileName);
            if(!file.exists() && !file.isDirectory()){
                file.mkdir();
            }
            OutputStream os = new FileOutputStream(file+"\\"+imgName+".jpg");
            while((len=inputStream.read(buf))!=-1){
                os.write(buf,0,len);
            }
            inputStream.close();
            os.close();
            httpURLConnection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
