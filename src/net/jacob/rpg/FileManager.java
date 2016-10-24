package net.jacob.rpg;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class FileManager {
	static int lvl;
	static int exp;
	static String name;
    public static void save() throws IOException{
    	PrintWriter outputFile = new PrintWriter("Saves.txt");
    	outputFile.println(RPG.ply.getLevel());
    	outputFile.println(RPG.ply.getExperience());
    	outputFile.println(RPG.ply.getName());
    	outputFile.close();
    }
    public static void load() throws FileNotFoundException { 	
    	File file = new File("Saves.txt");
    	if(file.exists()){
    		Scanner read = new Scanner(file);
    		lvl = read.nextInt();
    		exp = read.nextInt();
    		name = read.next();  		
    		read.close();
    	}else{
    		JOptionPane.showMessageDialog(null, "File Failed to load!\nCould not load stats!", "Error", 0);
    		lvl = 1;
    	}
    }
}
