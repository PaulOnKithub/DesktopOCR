package com.desktopocr.desktopocr;

//import com.google.api.gax.core.FixedCredentialsProvider;
//import com.google.auth.oauth2.ServiceAccountCredentials;
//import com.google.cloud.vision.v1.*;
//import com.google.cloud.vision.v1.Feature.Type;
//import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class CloudOCR {
    {
        /*ImageAnnotatorSettings settings =
                ImageAnnotatorSettings.newBuilder()
                        .setCredentialsProvider(FixedCredentialsProvider.create(ServiceAccountCredentials.
                                fromStream(new FileInputStream("brave-drummer-340719-b7c878868c8f.json"))))
                        .build();
        ImageAnnotatorClient client = ImageAnnotatorClient.create(settings);

        byte[] data = Files.readAllBytes(Paths.get("Pen.jpg"));
        ByteString imgBytes = ByteString.copyFrom(data);

        // Builds the request
        List<AnnotateImageRequest> requests = List.of(
                AnnotateImageRequest.newBuilder()
                        .addFeatures(Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION))
                        .setImage(Image.newBuilder().setContent(imgBytes))
                        .build()
        );

        // Performs the OCR request
        List<AnnotateImageResponse> responses = client.batchAnnotateImages(requests).getResponsesList();

        // Prints the recognized text from the response
        for (AnnotateImageResponse response : responses) {
            for (EntityAnnotation annotation : response.getTextAnnotationsList()) {
                System.out.println(annotation.getDescription());
            }
        }

        // Closes the client
        client.close();

    }
         */


    }
}
