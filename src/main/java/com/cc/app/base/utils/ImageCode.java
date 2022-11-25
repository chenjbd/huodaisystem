package com.cc.app.base.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 图片验证码
 * Created by Administrator on 2018/12/18.
 */
public class ImageCode {
    int w=79;//宽度
    int h=30;//高度

    private String validateCode = "";//验证码

    Random r=new Random();

    public  BufferedImage CreateImage(){//生成图片的方法
        BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);//内存中创建一张图片
        Graphics gps=img.getGraphics();//获取当前图片的画笔

        gps.setColor(new Color(240,240,240));//设置画笔

        gps.fillRect(0, 0, w, h);//填充一个与图片一样大小的矩形（其实就是为了设置背景颜色）
        return img;


    }
    public  BufferedImage getImage(){//得到图片的方法
        BufferedImage img=CreateImage();
        Graphics gps=img.getGraphics();//获取当前图片的画笔
        StringBuilder sb = new StringBuilder();
        //开始画东西
        for(int i=0;i<4;i++){
            String ch=this.getContent();
            sb.append(ch);
            gps.setColor(this.getColor());
            gps.setFont(this.getFont());
            gps.drawString(ch, w/4*i, h-5);//宽度让其不满图片
        }
        drawLine(img);
        validateCode = sb.toString();
        return img;

    }
    public  void saveImage(BufferedImage img,OutputStream out) throws  IOException {
        //这里为了测试将生成的图片放入f盘，在实际的项目开发中是需要将生成的图片写入客户端的:
        ImageIO.write(img, "JPEG", out);//response.getOutputStream()

        //ImageIO.write(img, "JPEG", new FileOutputStream("F:\\a.jpg"));//保存到硬盘

    }
    //在图片中插入字母和十个数字
//    String str="abcdefghijklmnupqrstuvwxyzABCDEFGHIJKLMNUPQRSTUVWZYZ1234567890";
    String str="1234567890";
    public   String getContent(){
        int index=r.nextInt(str.length());
        return str.charAt(index)+"";
    }
    String[] font={"宋体","华文楷体","华文隶书","黑体","华文新魏"};//字体
    int[] fontSize={24,25,26,27,28};//字号大小
    int[] fontStyle={0,1,2,3};//字体样式
    public   Font getFont(){
        int index1=r.nextInt(font.length);
        String name=font[index1];
        int style=r.nextInt(4);
        int index2=r.nextInt(fontSize.length);
        int size=fontSize[index2];
        return new Font(name,style,size);
    }
    public   Color getColor(){//得到不同的颜色
        int R=r.nextInt(256);//取值范围是0-255
        int G=r.nextInt(256);
        int B=r.nextInt(256);
        return new Color(R,G,B);
    }
    public void drawLine(BufferedImage img){//画干扰线
        Graphics2D gs=(Graphics2D) img.getGraphics();
        gs.setColor(Color.BLACK);
        gs.setStroke(new BasicStroke(1.0F));//设置线的宽度
        for(int i=0;i<2;i++){//横坐标不能超过宽度，纵坐标不能超过高度
            int x1=r.nextInt(w);
            int y1=r.nextInt(h);
            int x2=r.nextInt(w);
            int y2=r.nextInt(h);
            gs.drawLine(x1, y1, x2, y2);

        }
    }

    public String getValidateCode(){
        return this.validateCode;
    }

    public static void main(String[] args) throws  IOException {
        ImageCode imagecode=new ImageCode();
        BufferedImage img=imagecode.getImage();
        imagecode.saveImage(img, new FileOutputStream("d:\\a.jpg"));
    }

}
