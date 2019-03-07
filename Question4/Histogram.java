// Gajan Sivanesan 
// 100425203   
// Assignment 1 Question 4
// Due Date: March/06/2019

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class Histogram extends Pane {
    
    private int[] finalCount;
    private int maxValue;
    private int paneHeight = 600;

    // function to calculate the maximum value
    public int getMaxValue() {
        int j = finalCount[0];
        for (int i = 1; i < finalCount.length;i++) {
         if (finalCount[i] > j ) {
                j = finalCount[i];
            }
        }
        return j;
    }

    public void setFinalCount (int[] finalCount) {
        this.finalCount = finalCount;
        maxValue = getMaxValue();
        generateHistogram();
    }   
 
    public void generateHistogram() {
        int x = 0;
        for (int i = 0; i < finalCount.length; i++) {
            double rectangleHeight = (double)finalCount[i]/maxValue * paneHeight; // calculate rectangle height
            Rectangle rectangle = new Rectangle(20, rectangleHeight);
            rectangle.setX(x);
            rectangle.setY(paneHeight- rectangleHeight);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);
            Text text = new Text((char)(i + 65) + " ");
            text.setX(x + 5); // aligns the letters with the bars in the graph
            text.setY(610); // static y coordinate for all the letters
            getChildren().addAll(rectangle, text);
            x += 20; // increments the x coordinate to space it out 
        }
    }

    public Histogram() {
        this.finalCount = new int[26];
        maxValue = 0;
        generateHistogram();
    }

}