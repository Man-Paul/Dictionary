package com.example.dictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class Dictionary extends Application {

    int yLine = 100;
    Label display;
    CreatingDict suggestions = new CreatingDict();
    ListView<String> wordsuggestion;

    Pane content(){
        Pane root = new Pane();
        root.setPrefSize(400,400);

        CreatingDict findMeaning = new CreatingDict();

        TextField textBox = new TextField();
        textBox.setPromptText("Please Enter a Word");
        textBox.setTranslateX(50);
        textBox.setTranslateY(yLine);
        textBox.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                //display.setText(textBox.getText());
                String word = textBox.getText();
                if(!word.isBlank()){// && word.length()>=3){
                    //Fetch & Binding Suggestion
                    wordsuggestion.getItems().clear();
                    wordsuggestion.getItems().addAll(suggestions.getSuggestions(word));
                }
                else
                    wordsuggestion.getItems().clear();
            }
        });

        display = new Label("");
        display.setTranslateX(50);
        display.setTranslateY(yLine+40);

        Button search = new Button("Search");
        search.setTranslateX(300);
        search.setTranslateY(yLine);
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String wordCheck = textBox.getText();
                if(wordCheck.isBlank()){
                    display.setText("Please enter a word");
                    display.setTextFill(Color.RED);
                }
                else{
                    display.setText(findMeaning.meaning(wordCheck));
                    display.setTextFill(Color.BLACK);
                }
            }
        });

        wordsuggestion = new ListView<>();
        wordsuggestion.setTranslateX(50);
        wordsuggestion.setTranslateY(yLine+80);
        //String[] list = {"abstain","absent","bark","coal"};
        //wordsuggestion.getItems().addAll(list);
        wordsuggestion.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectWord = wordsuggestion.getSelectionModel().getSelectedItem();
                display.setText(findMeaning.meaning(selectWord));
                display.setTextFill(Color.BLACK);
            }
        });

        root.getChildren().addAll(textBox,search,display,wordsuggestion);

        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(content());
        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}