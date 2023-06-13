module com.desktopocr.desktopocr {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires tess4j;
    requires opencv;
    requires com.sun.jna;
    requires jai.imageio.core;
    requires google.cloud.vision;

    opens com.desktopocr.desktopocr to javafx.fxml;
    exports com.desktopocr.desktopocr;
}