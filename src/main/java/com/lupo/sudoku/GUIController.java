package com.lupo.sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class GUIController {

	@FXML
    private Label warningOutput;
	
    @FXML
    private Button solveInput;

    @FXML
    private RadioButton cheatingDisabledInput;

    @FXML
    private Button hintInput;

    @FXML
    private Button reloadInput;

    @FXML
    private RadioButton cheatingOnInput;

    @FXML
    private Label emptyBoxOutput;

    @FXML
    private TextField emptyBoxInput;

    @FXML
    private Label cheatingOutput;
    
   public void reload() {
    	
	   warningOutput.setText("");
	   
	   String amountOfBoxes = emptyBoxInput.getText();
	   
	   if (!amountOfBoxes.contentEquals("")) {
		  
		   try {
			   
			   int amountOfBoxesNumber = Integer.parseInt(amountOfBoxes);
			   
			   if (amountOfBoxesNumber < 0 | 81 < amountOfBoxesNumber) {
				   
				   warningOutput.setText("There are only a total of 81 boxes.");
				   return;
				   
			   }
			   
			   Main.settings.missingBoxes = amountOfBoxesNumber;
			   
			   if (amountOfBoxesNumber == 1) emptyBoxOutput.setText(amountOfBoxesNumber + " Box");
			   else emptyBoxOutput.setText(amountOfBoxesNumber + " Boxes");
			   
		   }
			   
		   catch (NumberFormatException e) {
			   
			   warningOutput.setText("The # of missing boxes is a whole NUMBER.");
			   return;
			   
		   }
		   
	   } 
	   
	   if (cheatingOnInput.isSelected()) {
		   
		   Main.settings.cheating = true;
		   cheatingOutput.setText("On");
		   
	   }
	   
	   if (cheatingDisabledInput.isSelected()) {
		   
		   Main.settings.cheating = false;
		   cheatingOutput.setText("Disabled");
		   
	   }
	   
	   
	   Main.settings.reload = true;
    	
   }
   
   public void solve() {
	   
	   if (Main.settings.cheating) Main.settings.quickSolve = true;
	   else  warningOutput.setText("You disabled cheating for this puzzle.");
	
   		
	   
   }
   
   public void hint() {
	   
	   if (Main.settings.cheating) Main.settings.hint = true;
	   else  warningOutput.setText("You disabled cheating for this puzzle.");
	   
   }
   
   public void makePermanentException() {
	   
	   warningOutput.setText("You cannot make 'nothing' permanent... Input a value first.");
	   
   }
   
    

}
