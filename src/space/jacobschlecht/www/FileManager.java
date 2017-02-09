package space.jacobschlecht.www;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class FileManager {
	private static int lvl;
	private static int exp;
	private static String name;
	private static int strength;
	private static int intillect;
	private static int stamina;
	private static int defence;
	
    public static void save(Player ply){
    	PrintWriter outputFile;
		try {
			outputFile = new PrintWriter("Saves.txt");
	    	outputFile.println(ply.getLevel());
	    	outputFile.println(ply.getExperience());
	    	outputFile.println(ply.getName());
	    	outputFile.println(ply.getStrength());
	    	outputFile.println(ply.getIntillect());
	    	outputFile.println(ply.getStamina());
	    	outputFile.println(ply.getDefence());
	    	outputFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error saving file!");
		}
    }
    
    public static Player load() {
    	File file = new File("Saves.txt");
    	try{
    		Scanner read = new Scanner(file);
    		lvl = read.nextInt();
    		exp = read.nextInt();
    		name = read.next();  		
    		strength = read.nextInt();
    		intillect = read.nextInt();
    		stamina = read.nextInt();
    		defence = read.nextInt();
    		 
    		read.close();
    		
    		return new Player(name, lvl, exp, strength, intillect, stamina, defence);
    	}
    	catch(Exception e) {
			JOptionPane.showMessageDialog(null, "File Failed to load!\nCould not load stats!", "Error", 0);
    		return new Player("Bob");
    	}
    }
}
