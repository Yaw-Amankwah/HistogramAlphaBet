package com.example.assignment3;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("Controller.fxml"));
        Scene scene1 = new Scene(root, 600,340);
        Stage stage = new Stage();
        stage.setScene(scene1);
        stage.show();

        String str = "the quick brown fox jumps over the lazy dog";
        IntegerProperty N = new SimpleIntegerProperty(3);

        int padding = 100;
        DecimalFormat df = new DecimalFormat("#.##");


        int n = 4;
        HistogramAlphaBet h = new HistogramAlphaBet(str);
        HistogramAlphaBet.MyPieChart p = h.new MyPieChart(n,new MyPoint((CANVASWIDTH/2)-(padding * 0.45),CANVASHEIGHT/2),Math.min((CANVASHEIGHT - padding)/2, (CANVASWIDTH - padding)/2) ,25);

        Pane container = new Pane(); //CONTAINER FOR TEXT NODES
        container.setPrefHeight(CANVASHEIGHT);
        container.setPrefWidth(CANVASWIDTH);

        StackPane pane = new StackPane();
        Scene scene = new Scene(pane, CANVASWIDTH,CANVASHEIGHT);
        Canvas canvas = new Canvas(CANVASWIDTH,CANVASHEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(linewidth);
        gc.setStroke(MyColor.WHITE.getJavaFXColor());



        int i = 0;
        double legendSide = 10;
        double dy = CANVASHEIGHT/28;
        double tPLCy = dy; //tPLC rectangle y
        double tPLCx = CANVASWIDTH - 85; //tPLC rectangle x
        MyPoint point = new MyPoint(tPLCx, tPLCy); // tPLC rectangle 
        double textX = tPLCx + 20; //text shifted 20 pixels to right of rectangle
        double textY = tPLCy; // text at same height as rectangle
        double sum_probabilities = 0;


       //GENERATE LEGEND
        for (Character Key: h.getProbability().keySet() ) {
            if (i < n) {
                MyRectangle rect = new MyRectangle(point, legendSide,legendSide, p.getSlices().get(Key).getColor());
                rect.draw(gc);
                point.shiftY(dy);//SHIFT SUBSEQUENT RECTANGLES 20 PIXELS DOWN
                Text t = new Text(String.valueOf(Key  + ": " + df.format(h.getProbability().get(Key))));
                t.setTextAlignment(TextAlignment.CENTER);
                t.setTextOrigin(VPos.CENTER);
                t.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
                t.setFill(MyColor.BLACK.getJavaFXColor());
                t.setX(textX);
                t.setY(textY);
                textY+=dy;
                container.getChildren().add(t);
                sum_probabilities += h.getProbability().get(Key);
                i++;
            }
            MyRectangle rect = new MyRectangle(point,legendSide,legendSide,MyColor.GRAY);
            rect.draw(gc);
        }

        Text t2 = new Text(textX,textY,"others: " + String.valueOf(df.format(1-sum_probabilities)));
        t2.setTextOrigin(VPos.CENTER);
        t2.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        t2.setFill(MyColor.BLACK.getJavaFXColor());

        p.printOut();
        p.draw(gc);

        container.getChildren().add(t2);
        pane.getChildren().addAll(canvas, container);
        primaryStage.setTitle("DRAW SLICE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch();
        final int CANVASWIDTH = 720;
        final int CANVASHEIGHT = 720;


    }
    final int CANVASWIDTH = 1080;
    final int CANVASHEIGHT = 720;
    final int linewidth = 5;
}