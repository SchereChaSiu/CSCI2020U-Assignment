// Gajan Sivanesan
// 100425203
// Assignment 1 Question 2
// Due Date: March/06/2019

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class Question2 extends Application {

    private TextField _investmentAmount;
    private TextField _years;
    private TextField _anInterestRate;
    private Button _calculate;
    private TextField _futureValue;

    private static DecimalFormat df2 = new DecimalFormat("####0.00");

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Question 2");

        // Grid Pane
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10,10,10,10));

        // investment amount field
        Label investmentLabel = new Label("Investment Amount");
        _investmentAmount = new TextField();
        gp.add(investmentLabel, 0,0);
        gp.add(_investmentAmount, 1, 0);

        // years field
        Label yearsLabel = new Label("Years: ");
        _years = new TextField();
        gp.add(yearsLabel, 0, 1);
        gp.add(_years, 1, 1);

        // annual interest rate field
        Label anInterestRateLabel = new Label("Annual Interest Rate: ");
        _anInterestRate = new TextField();
        gp.add(anInterestRateLabel, 0, 2);
        gp.add(_anInterestRate, 1, 2);

        // future value field
        Label futureValueLabel = new Label("Future Value: ");
        _futureValue = new TextField();
        _futureValue.setDisable(true); // makes the user unable to inderact with the textdfield
        gp.add(futureValueLabel, 0,3);
        gp.add(_futureValue, 1, 3);

        _calculate = new Button("Calculate");
        _calculate.setDefaultButton(true); // sets button to execute when enter is pressed
        gp.add(_calculate, 1, 4);

        // Button handler
        _calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                double years = Double.parseDouble(_years.getText());
                double investmentAmount = Double.parseDouble(_investmentAmount.getText());
                double anInterestRate = Double.parseDouble(_anInterestRate.getText());

                // formula for calculating the future value
                double futureValue = investmentAmount* (Math.pow((1 + ((anInterestRate/100)/12)), (years*12)));

                String finalValue = Double.toString(futureValue);

                _futureValue.appendText(finalValue);
                
            }
        });
        Scene scene = new Scene(gp, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
