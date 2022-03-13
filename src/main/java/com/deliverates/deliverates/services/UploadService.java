package com.deliverates.deliverates.services;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class UploadService {

    public String parseUploadedImage(MultipartFile image){

        try {
            byte[] bytesUpload = image.getBytes();
            BufferedImage bufferedImageUpload = ImageIO.read(new ByteArrayInputStream(bytesUpload));

            //Convert JPG/JPEG screenshot to the PNG(Tesseract supporting format)
            ByteArrayOutputStream byteArrayPng = new ByteArrayOutputStream();
            ImageIO.write(bufferedImageUpload, "png", byteArrayPng);

            byte[] bytesPng = byteArrayPng.toByteArray();
            BufferedImage bufferedImagePng = ImageIO.read(new ByteArrayInputStream(bytesPng));

            //Retrive text from ONG screenshot
            ITesseract ocrInstance = new Tesseract();
            ocrInstance.setDatapath("C:\\Users\\Yukniavichus\\Desktop\\Tess4J\\tessdata");

            return ocrInstance.doOCR(bufferedImagePng);

        } catch (IOException | TesseractException e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage());
            return "Can't parse this image, check StackTrace";
        }
    }

}
