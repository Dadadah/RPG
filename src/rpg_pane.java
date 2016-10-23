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

public class rpg_pane extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8837602785864913600L;
	static public JLabel curtext;
	static JLabel health;
	static JLabel level;
	static JLabel xp;
	static public JLabel mobhealth;
	static public JButton forward;
	static public JButton no;
	static JPanel infowrap;
	static JPanel healthbaroutline;
	static JPanel healthbar;
	JPanel bottomwrap;
	GridBagConstraints cons;
	Border border= BorderFactory.createLineBorder(Color.BLACK);
	Container frame = getContentPane();
	public static int ticker = 0;
	
	public static rpg_pane rpg;
	
	public rpg_pane(){
		super("UltraRPG");

		forward = new JButton("Continue");
		forward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (ticker) {
				case 0: rpg_base.cont++;
					break;
				case 1:	rpg_mobs.d++;
					break;
				case 2: rpg_base.yes++;
					break;
				}
		}});
		forward.setPreferredSize(new Dimension(200, 30));
		
		no = new JButton("Back to town");
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (ticker) {
				case 0: break;
				case 1:	rpg_mobs.k++;
						rpg_mobs.d = -100;
						rpg_base.cont = 0;
						rpg_base.yes = 0;
						rpg_base.no = 0;
					break;
				case 2: rpg_base.no++;
					break;
				}
		}});
		no.setPreferredSize(new Dimension(200, 30));
		
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
		
		infowrap = new JPanel(new GridLayout(2,2));
		infowrap.setPreferredSize(new Dimension(200, 30));
		infowrap.add(health);
		infowrap.add(mobhealth);
		infowrap.add(level);
		infowrap.add(xp);
		
		healthbar = new JPanel();
		healthbar.setBorder(border);
		healthbar.setBackground(Color.RED);
		healthbar.setPreferredSize(new Dimension(198, 28));
		
		healthbaroutline = new JPanel(new BorderLayout());
		healthbaroutline.setBorder(border);
		healthbaroutline.setPreferredSize(new Dimension(200, 30));
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
	
	public static void createFrame() {
		rpg = new rpg_pane();
	}

	public static void setDialog(String dialog) {
		curtext.setText(dialog);
		curtext.validate();
	}
	
	public static void healthchange() {
		int healthamt = rpg_base.health;
		int maxhealth = 50+(rpg_base.level*50);
		double ratio;
		int size;
		
		if (healthamt < 0) {
			healthamt = 0;
		}
		
		ratio = (healthamt*198) / maxhealth;
		
		size = (int) Math.round(ratio);
		
		healthbar.setPreferredSize(new Dimension(size, 28));
		health.setText("Health: " + healthamt);
		health.validate();
		healthbaroutline.remove(healthbar);
		healthbaroutline.add(healthbar, BorderLayout.LINE_START);
	}
	
	public static void mobhealthchange(int mobhealthamt) {
		
		mobhealth.setText("Monster HP: " + mobhealthamt);
		mobhealth.validate();
		
	}
	
	public static void lvlchange(){
		
		level.setText("Level: " + rpg_base.level);
		level.validate();
		
	}
	
	public static void xpchange(){
		
		xp.setText("Exp: " + rpg_base.experience + "/100");
		xp.validate();
		
	}
	
}
