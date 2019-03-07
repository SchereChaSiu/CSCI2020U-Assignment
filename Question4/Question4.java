// Gajan Sivanesan
// 100425203
// Assignment 1 Question 4
// Due Date: March/06/2019

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import java.io.File;
import java.net.URI;
import java.util.Scanner;

public class Question4 extends Application {

    private TextField _fileInput;
    private Button _viewBtn;
    private Label _fileInputLabel;

    static int [] finalCount = new int[26];

    // parse using ASCII
    public static void countLetters(String input) {
        for(int i = 0; i < input.length(); i++) {
            char letters = input.charAt(i);
            
            if(Character.isLetter(letters)) {
                finalCount[(int)letters - 97]++; 
            }
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        _fileInputLabel = new Label("File Path:");
        _fileInput = new TextField();
        _viewBtn = new Button("Calculate");
        _viewBtn.setDefaultButton(true); // sets button to execute when enter is pressed
        Histogram histogram = new Histogram();
   

        // Button handler
        // I have no Idea how to fix this.
        // I don't quite understand how URI works 
        // assignment shows the path to the file should he an option to use the program but I can't get it working
        // It works if the file is in the same directory and you just type the filename
        _viewBtn.setOnAction(e -> { 
            finalCount = new int[26];
            try {
                //String fileURL = _fileInput.getText();
                //URI uri = new URI(fileURL);
                URI uri = new URI(_fileInput.getText());
                File file;

                if (uri.isAbsolute()) {
                    file = new File(uri);
                    } 
                else {
                    file = new File(uri.toString());
                    }
                    // converts text to lower case 
                    try(Scanner input = new Scanner(file)) {
                        StringBuilder builder = new StringBuilder();
                        while (input.hasNext()) {
                            builder.append(input.nextLine().toLowerCase() + "\n");
                        }

                        countLetters(builder.toString());
                        histogram.setFinalCount(finalCount);
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                      }
                 
                }
                catch (Exception ex) {
                System.out.println(ex);
                }
        });

        HBox box = new HBox(20);
        box.getChildren().addAll(_fileInputLabel, _fileInput, _viewBtn);

        BorderPane pane = new BorderPane();
        pane.setCenter(histogram);
        pane.setBottom(box);
        pane.setMargin(histogram, new Insets(30));

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Question 4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}


