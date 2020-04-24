package com.dhcc.magiceyes;

import com.dhcc.magiceyes.exceptions.BusinessException;
import com.dhcc.magiceyes.utils.Base64Util;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.ImageRecognitionRequest;
import com.tencentcloudapi.faceid.v20180301.models.ImageRecognitionResponse;


public class FaceIdClientTest {

    public static void main(String[] args){

        try {
            String secretId = "AKIDLQFnC3G7CnBOM6jJbQ6dv44sY2KMNhVz";
            String secretKey = "QcbHgbGkxwgdwIOK1WbQTAdtvmU2R03q";

            Credential credential = new Credential( secretId , secretKey );

            /*FaceidClient faceidClient = new FaceidClient( credential ,"" );

            LivenessRecognitionRequest request = new LivenessRecognitionRequest();
            request.setIdCard( "430281199209187436" ); // 身份证号
            request.setName( "陈滨" ); // 名称
            request.setVideoBase64( "" ); // 用于活体检测的视频，视频的BASE64值；
            request.setLivenessType( "SILENT" ); // 活体类型 ： 静默


            LivenessRecognitionResponse response = faceidClient.LivenessRecognition( request );
            System.out.println( "Result : "+response.getResult() );
            System.out.println( "Description : "+response.getDescription() );
            System.out.println( "BestFrameBase64 : "+response.getBestFrameBase64() );
            System.out.println( "Sim : "+response.getSim() );
            System.out.println( "RequestId : "+response.getRequestId() );*/


            FaceidClient client = new FaceidClient( credential , "ap-chongqing" ); // 证书 ， 区域不可为 null || ""

            ImageRecognitionRequest request = new ImageRecognitionRequest();
            request.setIdCard( "430281199209187436" ); // 身份证号
            request.setName("陈滨"); // 姓名
            String fileName = "C:/Users/16105/Desktop/东华住云/陈滨.png"; // 文件路径
            request.setImageBase64( Base64Util.getImageBase64(fileName) ); // base64加密的图片

            ImageRecognitionResponse response = client.ImageRecognition( request ); // 获取图片识别响应
            System.out.println( "RequestId : " + response.getRequestId() ); // 请求ID
            System.out.println( "Result : " + response.getResult() ); // 响应结果
            System.out.println( "Sim : " + response.getSim() ); // 相似度
            System.out.println( "Description : " + response.getDescription() ); // 描述
            if( Boolean.valueOf(response.getResult())){ // 响应结果
                System.out.println("图片相识度："+response.getSim());
            } else {
                System.out.println( response.getDescription() );
            }
        } catch (TencentCloudSDKException e) {
            System.out.println(e.getMessage());
            throw new BusinessException("系统发生异常\n"+e.getMessage());
        }

    }


    /*public String verifyImageRecognition( String secretId , String secretKey , String idCard , String name , byte[] data ){
        try {
            Credential credential = new Credential( secretId , secretKey );
            FaceidClient client = new FaceidClient( credential , "ap-chongqing" ); // 证书 ， 区域不可为 null || ""

            ImageRecognitionRequest request = new ImageRecognitionRequest();
            request.setIdCard( idCard ); // 身份证号
            request.setName( name ); // 姓名
            request.setImageBase64( Base64Util.getByteArray2Base64Str( data ) ); // base64加密的图片

            ImageRecognitionResponse response = client.ImageRecognition( request ); // 获取图片识别响应

            System.out.println( "RequestId : " + response.getRequestId() ); // 请求ID
            System.out.println( "Result : " + response.getResult() ); // 响应结果
            System.out.println( "Sim : " + response.getSim() ); // 相似度
            System.out.println( "Description : " + response.getDescription() ); // 描述

            String result = null;
            if( Boolean.valueOf(response.getResult())){ // 响应结果
                System.out.println("图片验证成功，相识度："+response.getSim());
                result = "图片验证成功，相识度："+response.getSim();
            } else {
                System.out.println( response.getDescription() );
                result = response.getDescription();
            }
            return result;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.getMessage());
            throw new BusinessException("系统发生异常\n"+e.getMessage());
        }
    }*/

}
