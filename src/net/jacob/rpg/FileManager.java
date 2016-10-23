package net.jacob.rpg;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class FileManager {
	static int lvl;
	static int esp;
	static String name;
    public static void save() throws IOException{
    	PrintWriter outputFile = new PrintWriter("Saves.txt");
    	outputFile.println(RPG.level);
    	outputFile.println(RPG.experience);
    	outputFile.println(RPG.Name);
    	outputFile.close();
    }
    public static void load() throws FileNotFoundException { 	
    	File file = new File("Saves.txt");
    	if(file.exists()){
    		Scanner read = new Scanner(file);
    		lvl = read.nextInt();
    		esp = read.nextInt();
    		name = read.next();  		
    		read.close();
    	}else{
    		JOptionPane.showMessageDialog(null, "File Failed to load!\nCould not load stats!", "Error", 0);
    		lvl = 1;
    	}
    }
}
