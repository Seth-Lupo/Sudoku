package com.lupo.sudoku; 

import processing.core.PApplet;

public class Box {

		
	int indX, indY, value;
	float posX, posY, size; 
		
	public Box(int indX, int indY, float size, int value) {
		
		this.indX = indX;
		this.indY = indY;
		
		this.size = size;
		
		this.posX = indX * size;
		this.posY = indY * size;
		
		this.value = value;
		
		
		
	}
	
	public void draw(PApplet drawer) {
		
		drawer.fill(255);
		drawer.rect(posX, posY, size, size);
		
		drawer.fill(0);
		drawer.textSize(80 * 7/9);
		drawer.textAlign(drawer.CENTER);
		drawer.text(value, posX + size/2, posY - size * (float) -0.79);
		
		
	}
	
	public void mouseClicked(PApplet drawer) {
		
		
	}
	
	public void keyPressed(PApplet drawer) {
		
		
	}
	
	
	
}
