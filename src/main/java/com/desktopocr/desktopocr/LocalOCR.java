package com.desktopocr.desktopocr;


import javafx.scene.image.Image;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import com.sun.jna.*;
import com.github.jaiimageio.plugins.tiff.*;

import java.awt.image.BufferedImage;

import static com.desktopocr.desktopocr.PreProcessing.matToBufferedImage;

public class LocalOCR {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    Tesseract tess;

    String performOCR(String imagePath)  {
        tess=new Tesseract();//Creating and configuring new tesseract object
        tess.setDatapath("C:\\Users\\User\\Desktop\\Devs\\JavaML\\Tesseract");
        tess.setLanguage("eng");

        Mat image= Imgcodecs.imread(imagePath);//Transforming passed image to Matrix form

        Mat preProcessedImage=PreProcessing.preProcessImage(image); //Pre Processing image
        BufferedImage img=matToBufferedImage(preProcessedImage);//Transforming pre-processed image matrix to image
        String recognizedText= null;
        //Performing OCR
        try {
            recognizedText = tess.doOCR(img);
        } catch (TesseractException e) {
            System.out.println(e);
        }
        return recognizedText;
    }

}
