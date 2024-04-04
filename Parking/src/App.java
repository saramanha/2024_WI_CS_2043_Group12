import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.sql.SQLException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.Map;
import java.util.HashMap;

public class App extends Application {
    
    private Label selectYourSpotLabel;
    private TextField durationField;
    private TextField licencePlateField;
    private TextField spotIdField;
    private Button selectedParkingButton = null;
    public enum ButtonState {
        DEFAULT, SELECTED, PURCHASED
    }
    private static Map<String, Button> parkingSpots = new HashMap<>();
    private VBox inputBox;
    
    final static String greenStyle = "-fx-background-color: #689d6a; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold';"
            + "-fx-font-size: 14px; -fx-min-width: 68px; -fx-min-height: 42px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 10, 0.1, 0, 3);";
    final static String hoveredStyle = "-fx-background-color: #8ec07c; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold';"
            + "-fx-font-size: 14px; -fx-min-width: 68px; -fx-min-height: 42px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 10, 0.1, 0, 3);";
    final static String selectedStyle = "-fx-background-color: #fe8019; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold';"
            + "-fx-font-size: 14px; -fx-min-width: 68px; -fx-min-height: 42px; -fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.7), 5, 0.0, 2, 2);";
    final static String purchasedStyle = "-fx-background-color: #a89984; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold';"
            + "-fx-font-size: 14px; -fx-min-width: 68px; -fx-min-height: 42px; -fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.7), 5, 0.0, 2, 2);";

    private void showSecondaryStage() {
        VBox secondaryLayout = new VBox(10);
        secondaryLayout.setAlignment(Pos.CENTER);
        secondaryLayout.setStyle("-fx-background-color: #fbf1c7;");
        spotIdField = new TextField();
        spotIdField.setPromptText("Enter Spot ID");
        setupTextFieldStyle(spotIdField, 180);
        spotIdField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                spotIdField.setText(newValue.toUpperCase());
            }
        });
            
        Button leaveLotButton = new Button("Leave Lot");
        setupButtonStyle(leaveLotButton, "#458588");
        leaveLotButton.setOnAction(e -> {
            String spotId = spotIdField.getText();
            freeParkingSpot(spotId);
            spotIdField.setText("");
        });
            
        secondaryLayout.getChildren().addAll(spotIdField, leaveLotButton);
            
        Scene secondScene = new Scene(secondaryLayout, 680, 450);
            
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Departure Terminal");
        secondaryStage.setScene(secondScene);
        secondaryStage.show();
    }      
             
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(43);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #fbf1c7;");

        GridPane grid = setupParkingGrid();
        root.getChildren().add(grid);

        StackPane overlayPane = new StackPane();
        overlayPane.setAlignment(Pos.CENTER);

        selectYourSpotLabel = new Label("SELECT YOUR SPOT");
        selectYourSpotLabel.setFont(Font.font("Arial Black", FontWeight.BLACK, 25));
        selectYourSpotLabel.setTextFill(Color.web("#1d2021"));

        inputBox = setupInputFields();
        inputBox.setVisible(false);

        overlayPane.getChildren().addAll(selectYourSpotLabel, inputBox);
        root.getChildren().add(overlayPane);

        Scene scene = new Scene(root, 450, 680);
        primaryStage.setTitle("Parking Lot Terminal");
        primaryStage.setScene(scene);
        primaryStage.show();

        showSecondaryStage();
    }

    private GridPane setupParkingGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 5; col++) {
                String spotId = "" + (char)('A' + col) + (row + 1);
                Button button = new Button(spotId);
                button.setStyle(greenStyle);
                button.setUserData(ButtonState.DEFAULT);

                button.setOnMouseEntered(e -> {
                    if (button.getUserData() == ButtonState.DEFAULT) {
                        button.setStyle(hoveredStyle);
                    }
                });
                button.setOnMouseExited(e -> {
                    if (button.getUserData() == ButtonState.DEFAULT) {
                        button.setStyle(greenStyle);
                    }
                });
                button.setOnAction(e -> {
                    if (button.getUserData() != ButtonState.PURCHASED) {
                        if (selectedParkingButton != null && selectedParkingButton.getUserData() == ButtonState.SELECTED) {
                            selectedParkingButton.setStyle(greenStyle);
                            selectedParkingButton.setUserData(ButtonState.DEFAULT);
                        }
                        button.setStyle(selectedStyle);
                        button.setUserData(ButtonState.SELECTED);
                        selectedParkingButton = button;
                        showInputFields();
                    }
                });
                parkingSpots.put(spotId, button);
                grid.add(button, col, row);
            }
        }
        return grid;
    }

    private VBox setupInputFields() {
        VBox inputBox = new VBox(10);
        inputBox.setAlignment(Pos.CENTER);

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
            } else if (newValue != null) {
                licencePlateField.setText(newValue.toUpperCase());
            }
        });

        Button purchaseButton = new Button("Purchase Parking");
        purchaseButton.setStyle("-fx-background-color: #458588; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 15px; -fx-min-width: 180px; -fx-min-height: 60px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 1);");
        purchaseButton.setOnMouseEntered(e -> purchaseButton.setStyle("-fx-background-color: #83a598; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 15px; -fx-min-width: 180px; -fx-min-height: 60px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 1);"));
        purchaseButton.setOnMouseExited(e -> purchaseButton.setStyle("-fx-background-color: #458588; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 15px; -fx-min-width: 180px; -fx-min-height: 60px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 1);"));

        purchaseButton.setOnAction(e -> {
            if (selectedParkingButton != null && !licencePlateField.getText().isEmpty() && !durationField.getText().isEmpty()) {
                selectedParkingButton.setUserData(ButtonState.PURCHASED);
                selectedParkingButton.setStyle(purchasedStyle);

                // Parking Lot Manager Parks Vehicle!
                ParkingLotManager parkingLotManager = null;
                try {
                    parkingLotManager = new ParkingLotManager();
                } catch (SQLException error) {
                    error.printStackTrace();
                }
                if (parkingLotManager != null) {
                    try {
                        parkingLotManager.parkVehicle(selectedParkingButton.getText(), licencePlateField.getText(), Double.parseDouble(durationField.getText()));
                    } catch (SQLException error) {
                        error.printStackTrace();
                    }
                }
            
                displayPurchaseMessage();
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
        inputBox.getChildren().addAll(durationBox, licencePlateField, purchaseButton);
        inputBox.setVisible(false);
        return inputBox;
    }

    private void displayPurchaseMessage() {
        inputBox.setVisible(false);

        selectYourSpotLabel.setText("THANK YOU!");
        selectYourSpotLabel.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            selectYourSpotLabel.setText("SELECT YOUR SPOT");
        }));
        timeline.play();
    }
    
    private void showInputFields() {
        selectYourSpotLabel.setVisible(false);
        inputBox.setVisible(true);
    }

    private void setupButtonStyle(Button button, String backgroundColor) {
        button.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 1);");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #83a598; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 1);"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 14px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 1);"));
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

    public static void freeParkingSpot(String spotId) {
        Platform.runLater(() -> {
            Button spotButton = parkingSpots.get(spotId);
            if (spotButton != null) {
                spotButton.setStyle(greenStyle);
                spotButton.setUserData(ButtonState.DEFAULT);
            }
        });
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}