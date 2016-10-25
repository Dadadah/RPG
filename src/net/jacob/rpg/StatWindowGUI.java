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
	public JLabel curtext;
	JLabel strength;
	JLabel intillect;
	JLabel stamina;
	JLabel defence;
	JButton statWindow;
	JPanel infoWrap;
	JPanel bottomwrap;
	GridBagConstraints cons;
	Border border= BorderFactory.createLineBorder(Color.BLACK);
	Container frame = getContentPane();
	
	public StatWindowGUI(){
		super("Stats");
		
		strength = new JLabel("Str: 3");
		strength.setPreferredSize(new Dimension(100, 10));

		intillect = new JLabel("Int: 5");
		intillect.setPreferredSize(new Dimension(100, 10));

		stamina = new JLabel("Stam: 1");
		stamina.setPreferredSize(new Dimension(100, 10));

		defence = new JLabel("Def: 1");
		defence.setPreferredSize(new Dimension(100, 10));
		
		infoWrap = new JPanel(new GridLayout(2,2));
		infoWrap.setPreferredSize(new Dimension(400, 60));
		infoWrap.add(strength);
		infoWrap.add(intillect);
		infoWrap.add(stamina);
		infoWrap.add(defence);
		
		bottomwrap = new JPanel(new GridBagLayout());
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridx = 1;
		cons.gridy = 0;
		bottomwrap.add(infoWrap, cons);
		
		frame.setLayout(new BorderLayout());
		frame.add(bottomwrap, BorderLayout.SOUTH);
		frame.add(curtext, BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}
	
}