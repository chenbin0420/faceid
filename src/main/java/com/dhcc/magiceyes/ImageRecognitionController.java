package com.dhcc.magiceyes;

import com.dhcc.magiceyes.dto.FaceidParam;
import com.dhcc.magiceyes.dto.Person;
import com.dhcc.magiceyes.exceptions.BusinessException;
import com.dhcc.magiceyes.utils.Base64Util;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.ImageRecognitionRequest;
import com.tencentcloudapi.faceid.v20180301.models.ImageRecognitionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cb
 * @date 2020/03/23
 */
@RestController
public class ImageRecognitionController {

    /**
     * @Value 读取properties|yaml文件中的配置 faceid: secretId: 设置的值
     */
    @Value("${faceid.secretId}")
    private String secretId;

    @Value("${faceid.secretKey}")
    private String secretKey;

    @Autowired
    private FaceidParam faceIDParam;

    public String verifyImageRecognition(FaceidParam faceIDParam, byte[] orignResource ){
        try {
            Credential credential = new Credential( secretId , secretKey );
            /*
             * 证书 ， 区域不可为 null || ""
             */
            FaceidClient client = new FaceidClient( credential , "ap-chongqing" );

            ImageRecognitionRequest request = new ImageRecognitionRequest();
            /*
             * IdCard : 身份证号
             * Name : 姓名
             * ImageBase64 : base64加密的图片
             */
            request.setIdCard( faceIDParam.getIdCard() );
            request.setName( faceIDParam.getName() );
            request.setImageBase64( Base64Util.getByteArray2Base64Str( orignResource ) );

            /*
             * 获取图片识别响应
             */
            ImageRecognitionResponse response = client.ImageRecognition( request );

            /*
             * RequestId : 请求ID
             * Result : 响应结果
             * Sim : 相似度
             * Description : 描述
             */
            System.out.println( "RequestId : " + response.getRequestId() );
            System.out.println( "Result : " + response.getResult() );
            System.out.println( "Sim : " + response.getSim() );
            System.out.println( "Description : " + response.getDescription() );

            String result = null;

            /*
             * 响应结果
             */
            if( Boolean.valueOf(response.getResult())){
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
    }

    @RequestMapping("/getFaceIDParam")
    public FaceidParam getFaceIDParam(){
        System.out.println( secretId + ":" + secretKey );
        return faceIDParam;
    }


    @Autowired
    private Person person;

    @RequestMapping("/getDemo")
    public Person getDemo(){
        return person;
    }

    @RequestMapping("/getMap")
    public Map<String,Object> getMap(){
        Map<String,Object> map = new HashMap<>();
        map.put( "secretId",secretId );
        map.put( "secretKey" , secretKey );
        map.put( "faceIDParam" , faceIDParam );
        map.put( "person" , person );
        return map;
    }

    public void test(){
        System.out.println("test");
    }

}
