package com.lupo.sudoku;

import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application {
	
		public GUIController controller;
		public FXMLLoader loader;
	
	public void initialize(){
		
		launch();
		
	}


	public void start(Stage primaryStage) throws InterruptedException, IOException{
		
		loader = new FXMLLoader((getClass().getResource("GUI.fxml")));
		Pane pane = (Pane) loader.load();
		controller = loader.getController();
		System.out.println("The controller: " + controller);
		
		Scene scene = new Scene(pane);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("");
		
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		primaryStage.setTitle("Sudoku");
		primaryStage.setResizable(false);
		
		primaryStage.show();
	

		primaryStage.setOnCloseRequest(e -> {
			
			Main.settings.running = false;
	
		});
		
	}
	
			
}
	
