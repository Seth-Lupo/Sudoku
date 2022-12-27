package com.lupo.sudoku;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;
import java.util.ArrayList;

public class Organizer implements Runnable {
	
	public Box[][] grid;
	public float size = 100 * 7/9;
	public Random random = new Random();
	public int missingBoxes;
	public boolean init = false;
	public Thread load = new Thread(this);
	public boolean solved = false;
	 
	
	public Organizer(int missingBoxes) {
		
		this.missingBoxes = missingBoxes;
		
	}
	
	public void initialize() {
		
		init = false;
		load = new Thread(this);
		load.start();
		
	}
	
	
	public void run() {
		
		int dim = 9;
		
		createGrid: while(true) {
			
			grid = new Box[dim][dim];
			
			for (int i = 0; i < dim; i++) {
				
				for (int j = 0; j < dim; j++) {

					ArrayList<Integer> previousIntList = new ArrayList<Integer>();
					
					newIntCheck: while(true) {
						
						rowCheck: for (int column = 0; column < dim; column++) {
							
							if (grid[column][j] != null) {
									
								int previousValue = grid[column][j].value;
								previousIntList.add(previousValue);
													
							} 	
											
						}
									
							
						columnCheck: for (int row = 0; row < dim; row++) {
								
							if (grid[i][row] != null) {
									
								int previousValue = grid[i][row].value;
								previousIntList.add(previousValue);
											
							}
									
						}
						
						int groupingX = 0, groupingY = 0;

						if (0 <= i & i <= 2) groupingX = 0;		
						else if (3 <= i & i <= 5) groupingX = 3;
						else if (6 <= i & i <= 8) groupingX = 6;
							
						if (0 <= j & j <= 2) groupingY = 0;		
						else if (3 <= j & j <= 5) groupingY = 3;
						else if (6 <= j & j <= 8) groupingY = 6;
							
							
						for (int a = groupingX; a < groupingX + 3; a++) {
							
							for (int b = groupingY; b < groupingY + 3; b++) {
								
								if (grid[a][b] != null) {
									
									int previousValue = grid[a][b].value;
									previousIntList.add(previousValue);
									//System.out.println(grid[a][b].value + " CONFLICTS WITH GROUPING");
											
								}
							
							}
									
						}
							
						if (chooseNewInt(previousIntList, dim) == null) {
							
							continue createGrid;
						
						} else {
							
							grid[i][j] = new Box(i, j, size, chooseNewInt(previousIntList, dim));
							break newIntCheck;
							
						
						}
					}
				}				
			}
		
			break createGrid;
			
		}
		
		ArrayList<Integer[]> previousIndexList = new ArrayList<Integer[]>();
		
		removeBoxes: for (int i = 0; i < missingBoxes; i++) {
			
			Integer[] missingBoxIndex = chooseNewIndex(previousIndexList, 8);
			
			if (missingBoxIndex == null) {
				
				i = i - 1;
				continue removeBoxes;
				
			}
			
			int indexX = missingBoxIndex[0];
			int indexY = missingBoxIndex[1];
			
			int value = grid[indexX][indexY].value;
			grid[indexX][indexY] = new MissingBox(indexX, indexY, size, value);
			
			previousIndexList.add(missingBoxIndex);
			
		}
		
		init = true;
		System.out.println("STOP");
		
	}	
	
	public void draw(PApplet drawer) {
		
		for (int i = 0; i < 9; i++) {
				
			for (int j = 0; j < 9; j++) {
				
				grid[i][j].draw(drawer);
				
			
			}
						
		}
		
		drawer.pushMatrix();
		
			drawer.strokeWeight(10);
			
			for(int i = 0; i < 4; i++) {

				drawer.line(0, size * 3 * i, 9 * size, size * 3 * i);
			
			}
			
			for(int j = 0; j < 4; j++) {

				drawer.line(size * 3 * j, 0, size * 3 * j, size * 9);
			
			}
		
		drawer.popMatrix();
		
		solved = checkSolved();
		Main.settings.solved = solved;
		
		if (solved) {
			
			drawer.fill(200, 255, 200, 80);
			drawer.rect(0, 0, 900, 900);
			
			
		}
		
		
	}
	
	public void mouseClicked(PApplet drawer) {
		
		for (int i = 0; i < 9; i++) {
				
			for (int j = 0; j < 9; j++) {
				
				
				grid[i][j].mouseClicked(drawer);
		
			}
		}			
	}
	
	public void keyPressed(PApplet drawer) {
		
		for (int i = 0; i < 9; i++) {
				
			for (int j = 0; j < 9; j++) {
				
				
				grid[i][j].keyPressed(drawer);
		
			}
		}			
	}
	
	
	public boolean checkSolved() {
		
		for (int i = 0; i < 9; i++) {
			
			for (int j = 0; j < 9; j++) {
				
				
				
				if (grid[i][j] instanceof MissingBox) {
					
					if (((MissingBox) grid[i][j]).input.equals("")) {
						
						System.out.println(((MissingBox) grid[i][j]).input.equals(""));
						return false;
						
					} 
					
				}
				
				ArrayList<Integer> incorrectIntList = new ArrayList<Integer>();
				
				rowCheck: for (int column = 0; column < 9; column++) {
					
					if (column != i) {
						
						if (grid[column][j] instanceof MissingBox) {
						
							try {
							
								int incorrectValue = Integer.parseInt(((MissingBox) grid[column][j]).input);
								incorrectIntList.add(incorrectValue);
							
							} catch(NumberFormatException e) {
							
								return false;
							
							}
						
						} else {
					
							int incorrectValue = grid[column][j].value;
							incorrectIntList.add(incorrectValue);
						
						} 	
					
					}
									
				}
							
					
				columnCheck: for (int row = 0; row < 9; row++) {
						
					if (row != j) {
							
						if (grid[i][row] instanceof MissingBox) {
							
							try {
							
								int incorrectValue = Integer.parseInt(((MissingBox) grid[i][row]).input);
								incorrectIntList.add(incorrectValue);
						
							} catch(NumberFormatException e) {
								
								return false;
								
							}	
							
						} else {
					
							int incorrectValue = grid[i][row].value;
							incorrectIntList.add(incorrectValue);
						
						} 	
									
					}
							
				}
				
				int groupingX = 0, groupingY = 0;

				if (0 <= i & i <= 2) groupingX = 0;		
				else if (3 <= i & i <= 5) groupingX = 3;
				else if (6 <= i & i <= 8) groupingX = 6;
					
				if (0 <= j & j <= 2) groupingY = 0;		
				else if (3 <= j & j <= 5) groupingY = 3;
				else if (6 <= j & j <= 8) groupingY = 6;
					
					
				for (int a = groupingX; a < groupingX + 3; a++) {
					
					for (int b = groupingY; b < groupingY + 3; b++) {
						
						if (a != i & b != j) {
							
							if (grid[a][b] instanceof MissingBox) {
							
								try {
								
									int incorrectValue = Integer.parseInt(((MissingBox) grid[a][b]).input);
									incorrectIntList.add(incorrectValue);
								
								} catch (NumberFormatException e) {
									
									return false;
								
								}
							
							
							} else {
						
								int incorrectValue = grid[a][b].value;
								incorrectIntList.add(incorrectValue);
							
							} 	
							
									
						}
					
					}
							
				}
				
				if (incorrectIntList.contains(grid[i][j].value)) return false;
				
				
			}
						
		}

		return true;
	}
	
	public void fastSolve() {
		
		for (int i = 0; i < 9; i++) {
			
			for (int j = 0; j < 9; j++) {
				
				if (grid[i][j] instanceof MissingBox) {
				
					MissingBox missingBox = (MissingBox) grid[i][j];
					
					missingBox.input = Integer.toString(missingBox.value);
					missingBox.cheated = true;
					missingBox.noteArray = new String[]{"", "", "", "", "", "", "", "", ""};
					
				}
				
			}
						
		}
		
	}
	
	public void hint() {
		
		ArrayList<MissingBox> inputList = new ArrayList<MissingBox>();
		
		for (int i = 0; i < 9; i++) {
			
			for (int j = 0; j < 9; j++) {
				
				if (grid[i][j] instanceof MissingBox) {
				
					MissingBox missingBox = (MissingBox) grid[i][j];
					
					try {
					
						if (missingBox.value != Integer.parseInt((missingBox.input)) | missingBox.selected) inputList.add(missingBox);
						
					} catch (NumberFormatException e) {
						
						inputList.add(missingBox);
						
					}
					
				}
				
			}
						
		}
		
		if (inputList.size() > 0) {
			
			MissingBox wrongInput = inputList.get(random.nextInt(inputList.size()));
			wrongInput.input = Integer.toString(wrongInput.value);
			wrongInput.noteArray = new String[]{"", "", "", "", "", "", "", "", ""};
			wrongInput.cheated = true;
			
		}
		
	}
	
	
	public Integer chooseNewInt(ArrayList<Integer> previousIntList, int range) {
		
		ArrayList<Integer> possibleIntList = new ArrayList<Integer>();
		
		//System.out.println("New Loop");
		
		checkPossibleInt: for (int possibleInt = 1; possibleInt <= range; possibleInt++) {
		
			//System.out.println("Entering Loop");
			
		 	for (int i = 0; i < previousIntList.size(); i++) {
			
		 		if (possibleInt == previousIntList.get(i)) {
		 			
		 			//System.out.println("Wrong Int: " + possibleInt);
		 			continue checkPossibleInt;
		 			
		 		}
		 		
			}
		 	
		 	possibleIntList.add(possibleInt);
	 		//System.out.println("One Option: " + possibleInt + " Length of List: " + possibleIntList.size());
		
		}
		
		if (possibleIntList.size() == 0) return null;
		
		int newInt = possibleIntList.get(random.nextInt(possibleIntList.size()));
		return newInt;
		
	}
	
	public Integer[] chooseNewIndex(ArrayList<Integer[]> previousIndexList, int range) {
		
		ArrayList<Integer[]> possibleIndexList = new ArrayList<Integer[]>();
		
		checkPossibleIndexX: for (int possibleIndexX = 0; possibleIndexX <= range; possibleIndexX++) {
		
			
			checkPossibleIndexY: for (int possibleIndexY = 0; possibleIndexY <= range; possibleIndexY++) {
			//System.out.println("Entering Loop");
			
				Integer[] possibleIndex = {possibleIndexX, possibleIndexY};	
				
				for (int i = 0; i < previousIndexList.size(); i++) {
				
				
			 		if (previousIndexList.get(i)[0] == possibleIndex[0] 
			 				& previousIndexList.get(i)[1] == possibleIndex[1]) {
			 		
			 			continue checkPossibleIndexY;
			 			
			 		}
			 		
				}
				
		 		possibleIndexList.add(possibleIndex);
		 	
			}
		 
		}
		
		if (possibleIndexList.size() == 0) return null;
		
		Integer[] newIndex = possibleIndexList.get(random.nextInt(possibleIndexList.size()));
		return newIndex;
		
	}

}
