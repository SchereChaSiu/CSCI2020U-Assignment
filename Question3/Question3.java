// Gajan Sivanesan 
// 100425203   
// Assignment 1 Question 3
// Due Date: March/06/2019

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;

public class Question3 extends Application {

    // calculate the length of each line 
    public static double getDistance(Line line) {
        double length;
        double xStart = line.getStartX();
        double xEnd = line.getEndX();
        double yStart = line.getStartY();
        double yEnd = line.getEndY();

        length = Math.sqrt(Math.pow(xStart - xEnd, 2)) + Math.sqrt(Math.pow(yStart - yEnd, 2)); 
        
        return length;
    }

    // calculate the angles 
    public static double getAngle(Line x, Line y, Line z) {
        double angle;
        double a = getDistance(x);
        double b = getDistance(y);
        double c = getDistance(z);

        angle = Math.toDegrees(Math.acos((a * a - b * b - c * c)/ (-2 * b * c)));

        return angle;

    }

    public static void main(String args[]) {
        launch(args);
    }
  
    @Override
    public void start(Stage primaryStage) {
        Circle[] smallCircles = new Circle[3];
        Line[] edges = new Line[3];
        Text[] labels = new Text[3];
        Pane pane = new Pane();
        int next;

        Circle mainCircle = new Circle(500,500,100);
        mainCircle.setFill(Color.WHITE);
        mainCircle.setStroke(Color.BLACK);
        pane.getChildren().add(mainCircle);

        // generate 3 small circles around the big circle 
        for(int i = 0; i < 3; i++) {
            Circle vertex = new Circle(10);
            double x = mainCircle.getCenterX() + 100*Math.cos(2*Math.PI*Math.random());
            double y = mainCircle.getCenterY() + 100*Math.sin(2*Math.PI*Math.random());

            vertex.setCenterX(x);
            vertex.setCenterY(y);
            smallCircles[i] = vertex;
            vertex.setOnMouseDragged(e -> {
                
                if(e.getButton().equals(MouseButton.PRIMARY)) {
                    double angle = Math.atan2(e.getY() - mainCircle.getCenterY(), e.getX() - mainCircle.getCenterX()); // calculates angle
                    Circle c = (Circle)e.getSource();
                    c.setCenterX(mainCircle.getCenterX() + 100 * Math.cos(angle));
                    c.setCenterY(mainCircle.getCenterY() + 100 * Math.sin(angle));
                    // find angles of each vertex
                    double angle1 = getAngle(edges[0], edges[1], edges[2]);
                    double angle2 = getAngle(edges[1], edges[2], edges[0]);
                    double angle3 = getAngle(edges[2], edges[0], edges[1]);
                    // set values to the circles
                    labels[0].setText(Double.toString(angle1));
                    labels[1].setText(Double.toString(angle2));
                    labels[2].setText(Double.toString(angle3));
                }
            });   
        }
        // connect the small circles
        for(int i = 0; i < 3; i++) {
             if(i == 2) {
                 next = 0;
             }
             else {
                 next = i + 1;
            }
            Line length = new Line();
            length.startXProperty().bind(smallCircles[i].centerXProperty());
            length.endXProperty().bind(smallCircles[next].centerXProperty());
            length.startYProperty().bind(smallCircles[i].centerYProperty());
            length.endYProperty().bind(smallCircles[next].centerYProperty());

            edges[i] = length;
        }

        // set the angles to the vertex
        for(int i = 0; i < 3; i++) {
            Text text = new Text();
            text.xProperty().bind(smallCircles[i].centerXProperty());
            text.yProperty().bind(smallCircles[i].centerYProperty().subtract(200));

            labels[i] = text;
        }

        pane.getChildren().addAll(smallCircles[0], smallCircles[1], smallCircles[2], edges[0], edges[1], edges[2], labels[0], labels[1], labels[2]);

        Scene scene = new Scene(pane, 900, 900);
        primaryStage.setTitle("Question 3");
        primaryStage.setScene(scene);
        primaryStage.show();
   
    }

    
}
