package com.eldar.NewHireDilemma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainScreen extends JFrame {
	/**
	 *  
	 */
	private static final long serialVersionUID = 7678729781863365268L;
	private JPanel paintPanel;
	private JPanel controlPanel;
	private JPanel globalPanel;
	private JTextField depthText;
	private JTextField noiseText;
	private JComboBox modelCombo;
	
	private MainScreen me = this;
	private Corp corp = Corp.getGoodCompany();
	
	public MainScreen() {
		this.addComponentListener(new ComponentAdapter() 
		{  
		        public void componentResized(ComponentEvent evt) {
		            me.resizeIt(me.getWidth(), me.getHeight());
		        }
		});		
		
		setTitle("A New Hire Dilemma");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);  

	   globalPanel = new JPanel(/*new FlowLayout()*/);
       globalPanel.setLayout(null);
       getContentPane().add(globalPanel);

       controlPanel = new JPanel(new FlowLayout());
       controlPanel.setBackground(Color.BLUE);
       globalPanel.add(controlPanel);
       
       // Depth:
       JLabel depthLabel = new JLabel("Depth:");
       depthLabel.setForeground(Color.YELLOW);
       controlPanel.add(depthLabel);
       depthText = new JTextField(" 5");
       controlPanel.add(depthText);

       // Noise:
       JLabel noiseLabel = new JLabel("Noise:");
       noiseLabel.setForeground(Color.YELLOW);
       controlPanel.add(noiseLabel);
       noiseText = new JTextField("20.0");
       controlPanel.add(noiseText);
       
       // Select model:
       modelCombo = new JComboBox();
       modelCombo.addItem("Good");   // #0
       modelCombo.addItem("Mixed");  // #1
       modelCombo.addItem("Poor");   // #2
       modelCombo.addItem("GoodX");  // #3
       modelCombo.addItem("MixedX"); // #4
       controlPanel.add(modelCombo);

       // Reset button.
       JButton resetButton = new JButton("Reset");
       resetButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
        	   switch (me.modelCombo.getSelectedIndex()) {
           	   default:
           	   case 0:
        		   corp = Corp.getGoodCompany();
        		   break;
           	   case 1:
        		   corp = Corp.getMixedCompany();
        		   break;
           	   case 2:
        		   corp = Corp.getPoorCompany();
        		   break;
           	   case 3:
        		   corp = CorpWithExtinction.getGoodCompany();
        		   break;
           	   case 4:
        		   corp = CorpWithExtinction.getMixedCompany();
        		   break;  
        	   }
        	   RunExperiment();
          }
       });
       controlPanel.add(resetButton);		
       
       // Run button.
       JButton runButton = new JButton("Run");
       runButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
        	   RunExperiment();
          }
       });
       controlPanel.add(runButton);		
       
       // Quit button.
       JButton quitButton = new JButton("Quit");
       quitButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               System.exit(0);
          }
       });
       controlPanel.add(quitButton);		
		
       paintPanel = new JPanel();
       paintPanel.setLayout(null);
       paintPanel.setBackground(Color.WHITE);
       globalPanel.add(paintPanel);					

       resizeIt(800, 500);
	}
	
	private void resizeIt(int width, int height) {
		final int controlIndent = 10;
		final int controlWidth = 120;
		setSize(width, height);
		
        globalPanel.setSize(new Dimension(width, height));
	    controlPanel.setSize(new Dimension(controlWidth, height));
        final int paintIndent = controlPanel.getWidth() + controlIndent;
        paintPanel.setBounds(paintIndent, 10, width - paintIndent - controlIndent, height - 40);
	}
	
	private void RunExperiment() {
		Graphics g = paintPanel.getGraphics();
	    Dimension d = paintPanel.getSize();
	    g.clearRect(0, 0, d.width, d.height);
	    corp.Draw(g, d.width, d.height);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainScreen ms = new MainScreen();
				ms.setVisible(true);
				ms.RunExperiment();
			}
		});
	}
}
