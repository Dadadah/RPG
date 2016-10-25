package net.jacob.rpg;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StatWindowGUI extends JFrame {
	 
	private static final long serialVersionUID = -6306962275032814101L;
	JLabel strength;
	JLabel intillect;
	JLabel stamina;
	JLabel defence;
	JButton statWindow;
	GridBagConstraints cons;
	Container frame = getContentPane();
	
	public StatWindowGUI(){
		super("Stats");
		
		strength = new JLabel("Str: 1");
		strength.setPreferredSize(new Dimension(100, 10));

		intillect = new JLabel("Int: 1");
		intillect.setPreferredSize(new Dimension(100, 10));

		stamina = new JLabel("Stam: 1");
		stamina.setPreferredSize(new Dimension(100, 10));

		defence = new JLabel("Def: 1");
		defence.setPreferredSize(new Dimension(100, 10));
		
		frame.setLayout(new GridBagLayout());
		
		cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridx = 1;
		cons.gridy = 0;
		frame.add(strength, cons);
		cons.gridx = 0;
		cons.gridy = 0;
		cons.anchor = GridBagConstraints.NORTH;
		frame.add(intillect, cons);
		cons.gridx = 1;
		cons.gridy = 1;
		frame.add(stamina, cons);
		cons.gridx = 0;
		cons.gridy = 1;
		frame.add(defence, cons);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}
	
}