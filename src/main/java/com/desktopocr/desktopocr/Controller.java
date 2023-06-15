package com.desktopocr.desktopocr;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import static javafx.scene.image.Image.*;

public class Controller implements Initializable {

    @FXML
    private Button Exit;
    @FXML
    private ChoiceBox<String> ocrOptions;

    @FXML
    private Label connectionLabel;

    @FXML
    private Button imageSelector;

    @FXML
    private ImageView imageViewArea;

    @FXML
    private Button resetButton;

    @FXML
    private TextArea resultsArea;

    @FXML
    private Button saveToFileButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button startButton;

    String imageURLPath;

    public static boolean isConnectedToInternet() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return address.isReachable(5000); // 5 seconds timeout
        } catch (UnknownHostException e) {
            // Host cannot be resolved
            return false;
        } catch (Exception e) {
            // Other exception occurred
            return false;
        }
    }

    @FXML
    void onExitAction(ActionEvent event) {
        Platform.exit();
        event.consume();
    }

    @FXML
    void onImageSelect(ActionEvent event) {
        FileChooser selectImage=new FileChooser();
        selectImage.setInitialDirectory(new File("C:\\"));
        selectImage.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File","*.png","*.jpg","*.jpeg"));
        //Get the current Stage
        Stage stage= (Stage) ((Node)event.getTarget()).getScene().getWindow();
        File selectedImage=selectImage.showOpenDialog(stage);
        if(selectedImage != null){
            try{
                imageURLPath=selectedImage.getPath();
                URL imageURL=selectedImage.toURI().toURL();
                Image image=new Image(imageURL.toString());
                imageViewArea.setImage(image);

            }catch(Exception e){
                System.out.println(e);
            }

        }

    }

    @FXML
    void onResetAction(ActionEvent event) throws MalformedURLException {
        ocrOptions.setValue(null);
        resultsArea.setText(null);

        Image placeHolderImage=new Image("file:\\E:\\Devs\\FX projects\\DesktopOCR\\placeholderImage.jpg");
        imageViewArea.setImage(placeHolderImage);

    }

    @FXML
    void onSaveToFileAction(ActionEvent event) {

    }

    @FXML
    void onSettingsClick(ActionEvent event) {

    }

    @FXML
    void onStartProcess(ActionEvent event) {
        String selectedMode=ocrOptions.getValue();
        String recognizedText;
        if(selectedMode!=null){
            if(selectedMode.equals("Local Processing")){
                LocalOCR localOCR=new LocalOCR();
                recognizedText=localOCR.performOCR(imageURLPath);
                resultsArea.setText(recognizedText);
            }else if (selectedMode.equals("Cloud Processing")){
                String cloudRecognizedText=CloudOCR.cloudFactoryMethod(imageURLPath);
                resultsArea.setText(cloudRecognizedText);
            }
        }
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        ocrOptions.getItems().addAll("Local Processing","Cloud Processing");
        boolean connectionResult;
        connectionResult=isConnectedToInternet();
        if(connectionResult){
            connectionLabel.setText("Connected !");
        }else{
            connectionLabel.setText("Offline !");
        }

    }
}
