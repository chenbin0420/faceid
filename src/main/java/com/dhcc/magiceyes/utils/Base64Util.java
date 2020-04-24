package com.dhcc.magiceyes.utils;

import com.dhcc.magiceyes.exceptions.BusinessException;
import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Base64Util {

    public static String getImageBase64( String fileName ) {
        InputStream reader = null; // 声明输入字节流
        byte[] data = null; // 声明字节数组
        try {
            reader = new FileInputStream( fileName ); // 创建 FileInputStream
            data = new byte[reader.available()]; // 创建 FileInputStream中内容长度的数组
            reader.read( data ); // 读取数据
        } catch(FileNotFoundException e){
            System.out.println("文件找不到，请确认文件路径:"+e.getMessage());
            throw new BusinessException("文件找不到，请确认文件路径");
        } catch (IOException e) {
            System.out.println("读数据时发生异常，请联系管理员:" + e.getMessage());
            throw new BusinessException("读数据时发生异常，请联系管理员");
        } finally {
            if( reader != null ){ // 非空条件下关闭流
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("关闭流时发生异常，请联系管理员:"+e.getMessage());
                    throw new BusinessException("关闭流时发生异常，请联系管理员");
                }
            }
        }
        return getByteArray2Base64Str( data );
    }

    public static String getByteArray2Base64Str( byte[] btyes ) {
        Base64 base64 = new Base64();
        return base64.encodeToString( btyes );
    }
}
