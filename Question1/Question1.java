//Gajan Sivanesan
//100425203
//Assignment 1 Question 1
//Due Date: March/6/2019

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Question1 extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // initialize a card dynamic array
        ArrayList<Integer> cards = new ArrayList<>();

        // fill the array with the string values of numbers since the cards have numbers in their name.
        for (int i = 0; i < 52 ; i++) {
            cards.add(i+1);
        }
        // function that randomizes the array
        java.util.Collections.shuffle(cards);

	
        ImageView viewCard1 = new ImageView(new Image( "Cards/"+cards.get(0) +".png"));
        ImageView viewCard2 = new ImageView(new Image("Cards/"+cards.get(1) +".png"));
        ImageView viewCard3 = new ImageView(new Image ("Cards/"+cards.get(2) +".png"));

	
        HBox display = new HBox();

        display.getChildren().add(viewCard1);
        display.getChildren().add(viewCard2);
        display.getChildren().add(viewCard3);

        Scene scene = new Scene(display, 230, 100);

        primaryStage.setTitle("Question 1");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}

