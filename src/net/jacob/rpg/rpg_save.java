package net.jacob.rpg;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class rpg_save {
	static int lvl;
	static int esp;
	static String name;
    public static void save() throws IOException{
    	PrintWriter outputFile = new PrintWriter("Saves.txt");
    	outputFile.println(rpg_base.level);
    	outputFile.println(rpg_base.experience);
    	outputFile.println(rpg_base.Name);
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
