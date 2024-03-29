import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class App extends Application {

    private TextField durationField;
    private TextField licencePlateField;
    private Button selectedParkingButton = null;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(60);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.TOP_CENTER);

        root.setStyle("-fx-background-color: #fbf1c7;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 5; col++) {
                String label = "" + (char)('A' + col) + (row + 1);
                Button button = new Button(label);
                button.setStyle("-fx-background-color: #689d6a; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px; -fx-min-width: 68px; -fx-min-height: 42px;");
                button.setUserData(false);
                button.setOnMouseEntered(e -> {
                    if (!(Boolean)button.getUserData()) {
                        button.setStyle("-fx-background-color: #8ec07c; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px; -fx-min-width: 68px; -fx-min-height: 42px;");
                    }
                });
                button.setOnMouseExited(e -> {
                    if (!(Boolean)button.getUserData()) {
                        button.setStyle("-fx-background-color: #689d6a; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px; -fx-min-width: 68px; -fx-min-height: 42px;");
                    }
                });
                button.setOnAction(e -> {
                    if (selectedParkingButton != null && !(Boolean)selectedParkingButton.getUserData()) {
                        selectedParkingButton.setStyle("-fx-background-color: #689d6a; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px; -fx-min-width: 68px; -fx-min-height: 42px;");
                    }
                    button.setStyle("-fx-background-color: #fe8019; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px; -fx-min-width: 68px; -fx-min-height: 42px;");
                    button.setUserData(true);
                    selectedParkingButton = button;
                }); // To do: Have the button return to green once a vehicle has left that spot <- handleButtonAction
        
                grid.add(button, col, row);
            }
        }

        HBox durationBox = new HBox(10);
        durationBox.setAlignment(Pos.CENTER);

        Button decrementButton = new Button("-");
        Button incrementButton = new Button("+");
        setupButtonStyle(decrementButton, "#458588");
        setupButtonStyle(incrementButton, "#458588");

        durationField = new TextField();
        durationField.setPromptText("Enter duration in hours");
        setupTextFieldStyle(durationField, 180);
        durationField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,1})?")) {
                durationField.setText(oldValue);
            } else if (!newValue.isEmpty()) {
                try {
                    double value = Double.parseDouble(newValue);
                    if (value < 0.5 || (2*value)%1 != 0) {
                        durationField.setText(oldValue);
                    }
                } catch (NumberFormatException e) {
                    durationField.setText(oldValue);
                }
            }
        });

        decrementButton.setOnAction(e -> adjustDuration(-0.5));
        incrementButton.setOnAction(e -> adjustDuration(0.5));
        durationBox.getChildren().addAll(decrementButton, durationField, incrementButton);

        licencePlateField = new TextField();
        licencePlateField.setPromptText("Enter licence plate");
        setupTextFieldStyle(licencePlateField, 180);
        licencePlateField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 7) {
                licencePlateField.setText(oldValue);
            }
        });

        Button purchaseButton = new Button("Purchase Parking");
        purchaseButton.setStyle("-fx-background-color: #458588; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 15px; -fx-min-width: 180px; -fx-min-height: 60px;");
        purchaseButton.setOnMouseEntered(e -> purchaseButton.setStyle("-fx-background-color: #83a598; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 15px; -fx-min-width: 180px; -fx-min-height: 60px;"));
        purchaseButton.setOnMouseExited(e -> purchaseButton.setStyle("-fx-background-color: #458588; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 15px; -fx-min-width: 180px; -fx-min-height: 60px;"));

        purchaseButton.setOnAction(e -> {
            if (selectedParkingButton != null && !durationField.getText().isEmpty() && !licencePlateField.getText().isEmpty()) {
                selectedParkingButton.setUserData(true); // mark button as purchased
                selectedParkingButton.setStyle("-fx-background-color: #928374; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px; -fx-min-width: 68px; -fx-min-height: 42px;");
                durationField.setText("");
                licencePlateField.setText("");
            } else {
                if (durationField.getText().isEmpty()) {
                    flashTextField(durationField);
                }
                if (licencePlateField.getText().isEmpty()) {
                    flashTextField(licencePlateField);
                }
            }
        });
               
        VBox inputBox = new VBox(10);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.getChildren().addAll(durationBox, licencePlateField, purchaseButton);

        root.getChildren().addAll(grid, inputBox);

        Scene scene = new Scene(root, 450, 700);
        primaryStage.setTitle("Parking Lot System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupButtonStyle(Button button, String backgroundColor) {
        button.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #83a598; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px;"));
    }

    private void setupTextFieldStyle(TextField textField, double width) {
        textField.setStyle("-fx-text-fill: black; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px; -fx-alignment: center;");
        textField.setPrefWidth(width);
        textField.setMaxWidth(width);
        textField.setMinWidth(width);
        textField.setPrefHeight(30); 
    }

    private void adjustDuration(double increment) {
        try {
            double currentValue = Double.parseDouble(durationField.getText().isEmpty() || durationField.getText().equals("Enter duration in hours") ? "0" : durationField.getText());
            double newValue = Math.max(0, currentValue + increment);
            durationField.setText(String.valueOf(newValue));
        } catch (NumberFormatException e) {
            durationField.setText("0");
        }
    }

    private void flashTextField(TextField textField) {
        final String originalStyle = textField.getStyle();
        final String flashStyle = originalStyle + "-fx-border-color: #cc241d; -fx-border-width: 2px;";
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0), e -> textField.setStyle(flashStyle)),
            new KeyFrame(Duration.seconds(0.2), e -> textField.setStyle(originalStyle)),
            new KeyFrame(Duration.seconds(0.4), e -> textField.setStyle(flashStyle)),
            new KeyFrame(Duration.seconds(0.6), e -> textField.setStyle(originalStyle)),
            new KeyFrame(Duration.seconds(0.8), e -> textField.setStyle(flashStyle)),
            new KeyFrame(Duration.seconds(1.0), e -> textField.setStyle(originalStyle))
        );
        timeline.play();
    }      

    private void handleButtonAction(String label) {
        // To do: this function, or multiple like it, will connect with the rest of the java/database
    }

    public static void main(String[] args) {
        launch(args);
    }
}
