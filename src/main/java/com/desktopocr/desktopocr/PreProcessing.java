package com.desktopocr.desktopocr;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class PreProcessing {

    static BufferedImage matToBufferedImage(Mat mat) {
        BufferedImage bufferedImage = new BufferedImage(mat.cols(), mat.rows(), BufferedImage.TYPE_BYTE_GRAY);
        final byte[] data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        mat.get(0, 0, data);
        return bufferedImage;
    }

    static Mat preProcessImage(Mat imageMat){
        Imgproc.cvtColor(imageMat,imageMat,Imgproc.COLOR_BGR2GRAY);
        Imgproc.medianBlur(imageMat,imageMat,3);
        return imageMat;
    }

}
