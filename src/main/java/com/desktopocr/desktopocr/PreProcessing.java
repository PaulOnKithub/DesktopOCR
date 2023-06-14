package com.desktopocr.desktopocr;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;
//import org.opencv.ximgproc.Ximgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.List;

public class PreProcessing {

    static BufferedImage matToBufferedImage(Mat mat) {
        BufferedImage bufferedImage = new BufferedImage(mat.cols(), mat.rows(), BufferedImage.TYPE_BYTE_GRAY);
        final byte[] data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        mat.get(0, 0, data);
        return bufferedImage;
    }

    static Mat preProcessImage(Mat imageMat){
        Imgproc.cvtColor(imageMat,imageMat,Imgproc.COLOR_BGR2GRAY);
        //Mat skewCorrected=skewCorrection(imageMat);
        //Mat noiseRemoved=noiseRemoval(skewCorrected);
        Mat thresholded = new Mat();
        Imgproc.adaptiveThreshold(imageMat, thresholded, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 11, 4);
        Imgproc.medianBlur(thresholded,thresholded,3);
        //Mat thinnedAndSkeletonized=performThinningAndSkeletonization(thresholded);
        return thresholded;
    }

    static Mat skewCorrection(Mat image){
        // Calculate the horizontal projection profile
        Mat projection = new Mat();
        Core.reduce(image, projection, 0, Core.REDUCE_SUM, CvType.CV_32F);

        // Find the skew angle
        double maxVal = Core.minMaxLoc(projection).maxVal;
        int maxIndex = -1;
        double threshold = maxVal * 0.9; // Adjust the threshold as needed
        for (int i = 0; i < projection.cols(); i++) {
            double value = projection.get(0, i)[0];
            if (value > threshold) {
                maxIndex = i;
                break;
            }
        }
        double skewAngle = -Math.atan((maxIndex - projection.cols() / 2.0) / projection.rows());
        double rotationAngle = Math.toDegrees(skewAngle);

        // Perform skew correction
        Mat rotated = new Mat();
        Mat rotationMatrix = Imgproc.getRotationMatrix2D(new Point(image.cols() / 2, image.rows() / 2), rotationAngle, 1.0);
        Imgproc.warpAffine(image, rotated, rotationMatrix, image.size(), Imgproc.INTER_LINEAR, Core.BORDER_CONSTANT, new Scalar(255));

        return rotated;
    }

    static Mat noiseRemoval(Mat inputImage){
        // Apply denoising
        Mat denoisedImage = new Mat();
        Photo.fastNlMeansDenoisingColored(inputImage, denoisedImage, 10, 10, 7, 21);

        return denoisedImage;

    }
    /*
    public static Mat performThinningAndSkeletonization(Mat inputImage) {
        // Perform morphological thinning operation
        Mat thinnedImage = new Mat();
        Ximgproc.thinning(inputImage, thinnedImage);

        // Perform morphological skeletonization operation
        Mat skeletonImage = new Mat();
        Ximgproc.thinning(inputImage, skeletonImage, Ximgproc.THINNING_ZHANGSUEN);

        return skeletonImage;
    }

     */

}
