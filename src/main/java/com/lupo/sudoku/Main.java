package com.lupo.sudoku;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class Main extends PApplet  {

	public static Organizer org;
	public static GUI gui;
	public static Settings settings;
	public static PImage loadingScreen, icon;
	
    public static void main(String[] args) {
    	
    	PApplet.main("com.lupo.sudoku.Main");
    	
    	settings = new Settings();
    	
       	gui = new GUI();
    	gui.initialize();
    	
    }

    public void settings(){
    	
    	size(695, 695);
    	
    	
    }

    public void setup(){
    	
    	org = new Organizer(settings.missingBoxes);
	    	
    	try {
    		
    		loadingScreen = new PImage( ImageIO.read(getClass().getResourceAsStream("loading.png")) );
	    	icon = new PImage( ImageIO.read(getClass().getResourceAsStream("icon.png")) );
	    	  
	    	surface.setTitle("Sudoku");
	    	surface.setIcon(icon);
    	
    	} catch (Exception e) {
    		
    	}
    	
    	//surface.setResizable(true);
    
    }

    private void setResizable(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void draw(){
        
		try {
	    	
			if(org.init) org.draw(this);	
	
	    	if (settings.reload) {
	    		
	    		if (!org.load.isAlive()) {
	    			
	    			background(255);
	    			image(loadingScreen, 350, 350, 200, 200);
	    			org.missingBoxes = settings.missingBoxes;
	    			org.initialize();
	    			
	    		}
	    		
	    		settings.reload = false;
	    	}
	    	
	    	if(settings.quickSolve) {
	    		
	    		org.fastSolve();
	    		settings.quickSolve = false;
	    		
	    	}
	    	
	    	if(settings.hint) {
	    		
	    		org.hint();
	    		settings.hint = false;
	    		
	    	}
	    	
	    	
	    	if (!settings.running) exit();
	    	
		} catch (Exception e) {
			
		}
    	
    }
    
    public void mouseClicked() {
    	
    	try {
    	
    		org.mouseClicked(this);
    		
    	} catch(Exception e) {
    		
    		
    	}
    	
    }
    
    public void keyPressed() {
    	
    	try {
    	
    		org.keyPressed(this);
    		
    	} catch(Exception e) {
    		
    		
    	}
    }
    
}