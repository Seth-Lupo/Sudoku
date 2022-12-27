package com.lupo.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PConstants;

public class MissingBox extends Box {

	String input;
	String[] noteArray = new String[]{"", "", "", "", "", "", "", "", ""};
	boolean selected = false, selectedNotes = false, notes = false;
	boolean cheated = false;
	
	
	public MissingBox(int indX, int indY, float size, int value) {
		
		super(indX, indY, size, value);
		input = "";
		
	}
	
	@Override
	public void draw(PApplet drawer) {
		
		if (selectedNotes) drawer.fill(150, 175, 200);
		else if (selected) drawer.fill(212, 175, 100);
		else drawer.fill(220);
		
		drawer.rect(posX, posY, size, size);
		
		if (notes) {
			
			drawer.fill(255, 245, 240);
			
			int order = 0;
			
			for (int j = 0; j < 3; j++) {	
			
				for (int i = 0; i < 3; i++) {
					
					drawer.fill(0);
					drawer.textSize(24 * 7/9);
					
				
					drawer.text(noteArray[order], 
						(float) (4 + posX + ((size-10)/3) * i + (size-10) * 0.171), (float) (5 + posY + ((size-10)/3) * j + (size-10) * 0.262));
				
					
					
					order++;
					
				}
				
			}
			
		}
		
		
		
		if (cheated) drawer.fill(220, 20, 20);
		else drawer.fill(0);
		drawer.textSize(80 * 7/9);
		drawer.textAlign(drawer.CENTER);
		drawer.text(input, posX + size/2, posY - size * (float) -0.79);
		
		
	}
	
	@Override
	public void mouseClicked(PApplet drawer) {
		
			boolean boundsX = (posX < drawer.mouseX) & (drawer.mouseX < posX + size);
			boolean boundsY = (posY < drawer.mouseY) & (drawer.mouseY < posY + size);
		
			if (boundsX && boundsY && !Main.settings.solved) selected = true;
			else { 
				
				selected = false;
				selectedNotes = false;
				
			}
			
		
	}
	
	@Override
	public void keyPressed(PApplet drawer) {
		
		if (selectedNotes && !Main.settings.solved) {
			
			
			
			switch(drawer.key) {
			
				case 49: 
					
					
					
					changeNote("1");
					
					
					break;
				
				
				case 50: 
					
					changeNote("2");
					break;
				
				
				case 51: 
					
					changeNote("3");
					break;
				
				
				case 52: 
					
					changeNote("4");
					break;
				
				
				case 53: 
					
					changeNote("5");
					break;
				
				
				case 54: 
					
					changeNote("6");
					break;
				
				
				case 55: 
					
					changeNote("7");
					break;
				
				
				case 56: 
					
					changeNote("8");
					break;
				
				
				case 57: 
					
					changeNote("9");
					break;
				
				
				case 8: 
					
					noteArray = new String[]{"", "", "", "", "", "", "", "", ""};
					break;
						
					
				
				
				case 'n': 
					
					selectedNotes = false;
					selected = false;
					break;
				
				
				default: break;
			
			}
			
		} else if (selected && !Main.settings.solved) {
			
			switch(drawer.key) {
			
				case 49: 
					
					changeInput("1");
					break;
				
				
				case 50: 
					
					changeInput("2");
					break;
				
				
				case 51: 
					
					changeInput("3");
					break;
				
				
				case 52: 
					
					changeInput("4");
					break;
				
				
				case 53: 
					
					changeInput("5");
					break;
				
				
				case 54: 
					
					changeInput("6");
					break;
				
				
				case 55: 
					
					changeInput("7");
					break;
				
				
				case 56: 
					
					changeInput("8");
					break;
				
				
				case 57: 
					
					changeInput("9");
					break;
				
				
				case 8: 
					
					changeInput("");
					break;
				
				
				case 'n': 
					
					selectedNotes = true; 
					notes = true;
					input = "";
					break;
				
				case 'p': 
					
					
					for (int i = 0; i < 9; i++) {
						
						for (int j = 0; j < 9; j++) {
							
							Box currentBox = Main.org.grid[i][j];
							
							if (currentBox.equals(this)) {
								
								if (input.equals(""));
								else Main.org.grid[i][j] = new Box(indX, indY, size, Integer.parseInt(input));
								
							}
							
						}
						
					}
					
					break;
				
				
				default: break;
			
			}
		
			if(drawer.keyCode == PConstants.ENTER) {
				
				selected = false;
				
			}
			
		}
		
	}
	
	public void changeNote(String input) {
		
		int inputNumber = Integer.parseInt(input);
		
		String currentInput = noteArray[inputNumber - 1];
		
		System.out.println(Arrays.toString(noteArray));
		
		if (currentInput.equals("") | currentInput == null) {
			
			noteArray[inputNumber - 1] = input;
			System.out.println("1st");
			
		} else {
			
			noteArray[inputNumber - 1] = "";
			
			
		}
		
	}
	
	public void changeInput(String input) {
		
		this.input = input; 
		
		notes = false; 
		noteArray = new String[]{"", "", "", "", "", "", "", "", ""};
		
	}
	
	

}
