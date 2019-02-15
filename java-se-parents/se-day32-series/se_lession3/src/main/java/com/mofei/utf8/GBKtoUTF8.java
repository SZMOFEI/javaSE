package com.mofei.utf8;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;

/**
 * @author mofei
 * @date 2019/1/20 10:02
 */
public class GBKtoUTF8 {

    public static void main(String[] args) throws Exception {

        String gbkDirPath = "I:\\other\\莫飞\\原系统盘备份\\基础视频学习资料\\javaSEVideo\\itcast_刘意\\";//GBK编码格式源码文件路径
        String utf8DirPath = "I:\\other\\莫飞\\原系统盘备份\\基础视频学习资料\\javaSEVideo\\itcast_刘意\\UTF8";//转为UTF-8编码格式源码文件保存路径
        @SuppressWarnings("unchecked")
        Collection<File> gbkFileList =  FileUtils.listFiles(new File(gbkDirPath), new String[]{"java"}, true);//获取所有java文件
        for (File gbkFile : gbkFileList) {
            String utf8FilePath = utf8DirPath + gbkFile.getAbsolutePath().substring(gbkDirPath.length());//UTF-8编码格式文件保存路径
            FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils.readLines(gbkFile, "GBK"));//使用GBK编码格式读取文件，然后用UTF-8编码格式写入数据
        }
    }

}
