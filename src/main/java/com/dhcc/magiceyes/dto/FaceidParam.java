package com.dhcc.magiceyes.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 人脸核身的参数类
 */
@Component
public class FaceidParam {
    /**
     * 需验证人的身份证号码
     */
    @Value("${faceid.secretId}")
    private String idCard;
    /**
     * 需验证人的姓名
     */
    @Value("${faceid.secretKey}")
    private String name;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
