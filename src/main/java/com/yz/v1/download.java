package com.yz.v1;

import com.yz.dao.dao;
import com.yz.entity.Custom;

import java.util.Iterator;
import java.util.List;


public class download {
    public static void main(String[] args) throws Exception{

        for(int i = 0;i <= 27175;) {
            List<Custom> list = dao.selectBack(i);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Custom custom = (Custom) it.next();
                String fileName = custom.getName();  //获取分类名字
                String imgurl = custom.getImgUrl();       //获取图片网址
                String imgName = custom.getId();       //获取Id作为图片名字
                okHttp.okIO(fileName,imgName,imgurl);
            }
            i=i+200;
            Thread.sleep(200);
        }

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("成功下载所有图片了");
    }
}
