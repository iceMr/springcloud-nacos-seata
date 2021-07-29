package com.example.Ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class OCRDemo {

    public static void main(String args[]) throws Exception {


        ITesseract instance = new Tesseract();
        //相对目录，这个时候tessdata目录和src目录平级
        //字库直接从github字库中查找
        //instance.setDatapath("tessdata");
        //支持绝对目录
        instance.setDatapath("C:\\Users\\Administrator\\Desktop\\Tess4J\\tessdata");
        //选择字库文件（只需要文件名，不需要后缀名）
        instance.setLanguage("chi_sim");
        try {
            File imageFile = new File("F:\\77.png");
            //开始识别
            String result = instance.doOCR(imageFile);
            //打印图片内容
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
