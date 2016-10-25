package net.jacob.rpg;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RPGGui extends JFrame{
	
	private static final long serialVersionUID = -8837602785864913600L;
	public JLabel curtext;
	JLabel health;
	JLabel level;
	JLabel xp;
	JLabel strength;
	JLabel intillect;
	JLabel stamina;
	JLabel defence;
	public JLabel mobhealth;
	public JButton forward;
	public JButton no;
	JButton statWindow;
	JPanel infowrap;
	JPanel healthbaroutline;
	JPanel healthbar;
	JPanel bottomwrap;
	GridBagConstraints cons;
	Border border= BorderFactory.createLineBorder(Color.BLACK);
	Container frame = getContentPane();
	public static int ticker = 0;
	
	public RPGGui(){
		super("UltraRPG");

		forward = new JButton("Continue");
		forward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (ticker) {
				case 0: 
					RPG.cont++;
					break;
				case 1:	
					RPG.cycle.threadSuspended = false;
					synchronized(RPG.cycle) {
						RPG.cycle.notify();
					}
					break;
				case 2: 
					RPG.cycle.yes++;
					break;
				}
		}});
		forward.setPreferredSize(new Dimension(200, 30));
		
		no = new JButton("Back to town");
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (ticker) {
				case 0: break;
				case 1:	
					if (RPG.cycle.k.equals("Town")) {
						RPG.cycle.k = "Dungeon";
					} else {
						RPG.cycle.k = "Town";
					}
					RPG.cont = 0;
					RPG.cycle.yes = 0;
					RPG.cycle.no = 0;
					break;
				case 2: 
					RPG.cycle.no++;
					break;
			
		
		
				}
		}});
		no.setPreferredSize(new Dimension(200, 30));
		
		statWindow = new JButton("Stats");
		statWindow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		});
		
		
		cons = new GridBagConstraints();
		
		curtext = new JLabel(" ");

		health = new JLabel("Health: 100");
		health.setPreferredSize(new Dimension(100, 10));
		
		mobhealth = new JLabel("Monster HP: 0");
		mobhealth.setVisible(false);
		mobhealth.setPreferredSize(new Dimension(100, 10));
		
		level = new JLabel("Level: 1");
		level.setPreferredSize(new Dimension(100, 10));
		
		xp = new JLabel("Exp: 0/100");
		xp.setPreferredSize(new Dimension(100, 10));
		
		strength = new JLabel("Str: 3");
		strength.setPreferredSize(new Dimension(100, 10));
		
		intillect = new JLabel("Int: 5");
		intillect.setPreferredSize(new Dimension(100, 10));
		
		stamina = new JLabel("Stam: 1");
		stamina.setPreferredSize(new Dimension(100, 10));
		
		defence = new JLabel("Def: 1");
		defence.setPreferredSize(new Dimension(100, 10));
		
		infowrap = new JPanel(new GridLayout(2,2));
		infowrap.setPreferredSize(new Dimension(400, 60));
		infowrap.add(health);
		infowrap.add(mobhealth);
		infowrap.add(level);
		infowrap.add(xp);
		infowrap.add(strength);
		infowrap.add(intillect);
		infowrap.add(stamina);
		infowrap.add(defence);
		
		healthbar = new JPanel();
		healthbar.setBorder(border);
		healthbar.setBackground(Color.RED);
		healthbar.setPreferredSize(new Dimension(398, 28));
		
		healthbaroutline = new JPanel(new BorderLayout());
		healthbaroutline.setBorder(border);
		healthbaroutline.setPreferredSize(new Dimension(400, 30));
		healthbaroutline.add(healthbar, BorderLayout.LINE_START);
		
		bottomwrap = new JPanel(new GridBagLayout());
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridx = 1;
		cons.gridy = 0;
		bottomwrap.add(forward, cons);
		cons.gridx = 0;
		cons.gridy = 0;
		cons.anchor = GridBagConstraints.NORTH;
		bottomwrap.add(healthbaroutline, cons);
		cons.gridx = 1;
		cons.gridy = 1;
		bottomwrap.add(no, cons);
		cons.gridx = 0;
		cons.gridy = 1;
		bottomwrap.add(infowrap, cons);

		frame.setLayout(new BorderLayout());
		frame.add(bottomwrap, BorderLayout.SOUTH);
		frame.add(curtext, BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void setDialog(String dialog) {
		curtext.setText(dialog);
		curtext.validate();
	}
	
	public void healthchange() {
		int healthamt = RPG.ply.getHealth();
		int maxhealth = RPG.ply.getMaxHealth();
		double ratio;
		int size;
		
		if (healthamt < 0) {
			healthamt = 0;
		}
		
		ratio = (healthamt*398) / maxhealth;
		
		size = (int) Math.round(ratio);
		
		healthbar.setPreferredSize(new Dimension(size, 28));
		health.setText("Health: " + healthamt);
		health.validate();
		healthbaroutline.remove(healthbar);
		healthbaroutline.add(healthbar, BorderLayout.LINE_START);
	}
	
	public void mobhealthchange(int mobhealthamt) {
		
		mobhealth.setText("Monster HP: " + mobhealthamt);
		mobhealth.validate();
		
	}
	
	public void refreshGUI() {
		level.setText("Level: " + RPG.ply.getLevel());
		xp.setText("Exp: " + RPG.ply.getExperience() + "/100");
		strength.setText("Str: " + RPG.ply.getStrength());
		intillect.setText("Int: " + RPG.ply.getIntillect());
		stamina.setText("Stam: " + RPG.ply.getStamina());
		defence.setText("Def: " + RPG.ply.getDefence());
		infowrap.validate();
	}	
	
}
