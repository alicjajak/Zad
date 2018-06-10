package lab13;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MainFrame extends JFrame{
	 JPanel drawPanel;
	 JPanel dataPanel;
	 
	 JLabel infoLabel;
	 JLabel aLabel;
	 JLabel bLabel;
	 
	 JTextField aField;
	 JTextField bField;
	 
	 JButton drawButton;
	 
	 final XYSeries line = new XYSeries("Funkcja liniowa");
	 final XYSeriesCollection dataFunctions = new XYSeriesCollection();
	 double a;			//wartoœæ parametru a
	 double b;			//wartoœæ parametru b
	 
	 JMenuBar menuBar;
	 JMenu menu;
	 
	 JMenuItem saveItem;
	 JMenuItem takeItem;
	 JMenuItem exitItem;
	 
	public MainFrame() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600,600);
		this.setTitle("Zadanie weryfikacyjne");
		this.setLayout(new BorderLayout());
		
		//MENU
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		
		saveItem = new JMenuItem("Zapisz wykres");
		menu.add(saveItem);
		takeItem = new JMenuItem("Wczytaj dane txt");
		menu.add(takeItem);
		exitItem = new JMenuItem("Zamknij");
		menu.add(exitItem);
		
		drawPanel = new JPanel();
		dataPanel = new JPanel();
		
		this.add(drawPanel, BorderLayout.PAGE_START);
		this.add(dataPanel, BorderLayout.WEST);
		this.add(menuBar);
		
		drawPanel.setBackground(Color.BLUE);
		dataPanel.setBackground(Color.RED);
		drawPanel.setLayout(new BorderLayout());
		dataPanel.setLayout(new GridLayout(6,1));
		
		infoLabel=new JLabel("Parametry prostej ax+b;");
		aLabel = new JLabel ("a:");
		bLabel = new JLabel ("b:");
		
		aField = new JTextField();
		bField = new JTextField();
		
		drawButton = new JButton("DRAW");
		
		dataPanel.add(infoLabel);
		dataPanel.add(aLabel);
		dataPanel.add(aField);
		dataPanel.add(bLabel);
		dataPanel.add(bField);
		
		drawPanel.add(drawButton, BorderLayout.PAGE_START);
				
	       //LISTENERY
		exitItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}
			
		});
		menuBar.add(menu);
		setJMenuBar(menuBar);

	    drawButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	a=Double.parseDouble(aField.getText());
	        	b=Double.parseDouble(bField.getText());
	        	
	        	for(double i = 0; i < 5; i += 0.5){
		    		double y = a*i+b;
		    		line.add(i, y);
		    		}
	        	
	        	dataFunctions.addSeries(line);
	        	JFreeChart chart = ChartFactory.createXYLineChart 
		    			("WYKRES WYBRANEJ FUNKCJI",  // Title 
		    					"X",           // X-Axis label 
		    					"Y",           // Y-Axis label 
		    					dataFunctions,          // Dataset 
		    					PlotOrientation.VERTICAL,        //Plot orientation 
		    					true,                //show legend 
		    					true,                // Show tooltips 
		    					false               //url show 
		    					); 

	        
	        final ChartPanel chartPanel = new ChartPanel(chart);
	        getContentPane().add(chartPanel);
	        	
	        getContentPane().add(chartPanel, BorderLayout.CENTER);
	        revalidate();
	         
	        }
	      });
	}

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
		

	}

}
