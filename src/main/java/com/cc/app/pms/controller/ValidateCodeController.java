package com.cc.app.pms.controller;

import com.cc.app.base.utils.ImageCode;
import com.cc.app.pms.service.CodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 生成图形验证码
 */
@RestController
@RequestMapping("/pms")
public class ValidateCodeController {

    private Logger logger = LoggerFactory.getLogger(ValidateCodeController.class);

    @Autowired
    private CodeService service;

    @GetMapping("/imgCode")
    public void creaeCode(HttpServletRequest request, HttpServletResponse response){
        ImageCode imagecode=new ImageCode();
        BufferedImage img=imagecode.getImage();
        try {
            String codeId = request.getParameter("r");//从前端传codeId
            String code = imagecode.getValidateCode();//验证码
            service.genImgCode(codeId, code);
            response.setContentType("image/jpeg");
            imagecode.saveImage(img, response.getOutputStream());
        } catch (IOException e) {
            logger.error("生成图片验证码异常", e);
        }
    }



}
