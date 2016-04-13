package GUI_Package;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class Heater extends JFrame{
//Conor Donohue 13404068
	private JLabel label1, label2, label3;
	private JButton onswitch,offswitch,summary;
	private JComboBox intensitylevels;
	private String[] levels = {"1","2","3","4","5"};
	private JTextField HeaterSummary;
	private JRadioButton manual,timer;
	private ButtonGroup radioGroup;
	public boolean onoff;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	private Container container;
	//create and iniatilse your variables
	public Heater(){
		super( "Heating System" );
		Box boxes[] = new Box[1];
		boxes[0] = Box.createHorizontalBox();
		// get content pane and set its layout
		 container = getContentPane();
		 layout = new GridBagLayout();
		 container.setLayout( layout );
		 constraints = new GridBagConstraints();
		 // use grid bag layout as your layout manager so you can position each component exactly where you want them
		 
		 // JLabel constructor with a string argument
		 label1 = new JLabel( "Do you wish to turn the Heating on or off?" );
		constraints.fill = GridBagConstraints.BOTH;
		addComponent (label1, 0,0,1,3);//add each component to the GUI
		 
		 
		 onswitch = new JButton("ON");
		 offswitch = new JButton("OFF");
		 boxes[0].add(onswitch);
		 boxes[0].add(Box.createHorizontalGlue());
		 boxes[0].add(offswitch);
		// container.add(boxes[0]);
		 constraints.fill = GridBagConstraints.BOTH;
		 addComponent(boxes[0],3,0,1,1);
		 ButtonHandler handler = new ButtonHandler();
		 onswitch.addActionListener(handler);
		 offswitch.addActionListener(handler);
		 //create uneditable text and buttons with event handlers
		 
		 label2 = new JLabel("What Heat Intensity level do you want:");
		 constraints.fill = GridBagConstraints.BOTH;	
		 addComponent(label2,0,3,1,1);
		 intensitylevels = new JComboBox( levels );
		 intensitylevels.setMaximumRowCount(5);
		 ListHandler lh =new ListHandler();
		 intensitylevels.addActionListener(lh);
		// container.add(intensitylevels);
		constraints.fill = GridBagConstraints.BOTH;
		addComponent(intensitylevels,2,3,1,1);
		 //create a drop down list set the max amount of items and event handlers
		 
		 label3 = new JLabel("Would of the two options do you want the heating system to be?");
		 manual = new JRadioButton("Manual");
		 timer = new JRadioButton("Timer");
		 radioGroup = new ButtonGroup();
		 radioGroup.add(manual);
		 constraints.fill = GridBagConstraints.BOTH;
		 addComponent(label3, 4,0,1,1);
		 addComponent(manual,5,0,1,1);
		 addComponent(timer,5,1,1,1);
		 radioGroup.add(timer);
		 RadioButtonHandler rbh = new RadioButtonHandler();
		 manual.addActionListener(rbh);
		 manual.setActionCommand("manual");
		 timer.addActionListener(rbh);
		 timer.setActionCommand("a timer");
		 // create radio buttons and use the radiogroup so only one option can be chosen at a time
		 
		 summary = new JButton("Click here for information on the Heater");
		 HeaterSummary = new JTextField(100);
		 constraints.fill = GridBagConstraints.BOTH;
		 addComponent(summary,6,0,1,1);
		 addComponent(HeaterSummary,7,0,5,1);
		 SummaryButton tfh = new SummaryButton();
		 summary.addActionListener(tfh);
		 //create a button which links to a textfield through the event handler
		 
		 //set the size of the GUI and make sure it is visible
		 setSize(800,200);
		 setVisible(true);
	}
	public static void main( String args[] )
	 {
		 Heater application = new Heater();
		
		 application.setDefaultCloseOperation(
		 JFrame.EXIT_ON_CLOSE );
	 }

	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if (event.getActionCommand()== "ON"){
				 onoff=true; //set the boolean so the summary can tell whether the system is on or not
			}
			JOptionPane.showMessageDialog(null,"The Heating System is now "+ event.getActionCommand());// It informs you of which you have chosen
		}
	}
	private class ListHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			JOptionPane.showMessageDialog(null, "The chosen Intensity Level is "+ intensitylevels.getSelectedItem());
		}//It tells you which option you have chosen
	}
	
	private class SummaryButton implements ActionListener{
		private String on;
		public void actionPerformed(ActionEvent event){
				if(onoff){
				 on="ON";// if the heater is on, ie boolean is TRUE, you want to print out ON
				}
				else{
					 on="OFF";//else the boolean is false and the heating is off
				}
			HeaterSummary.setText("The Heater is switched " + on +" at the moment. The intensity level is at "+ intensitylevels.getSelectedItem()+". The heating System is on "+radioGroup.getSelection().getActionCommand());
		}// you set the text in the textfield to contain the details of the heating system
		
		
	}
	private class RadioButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			JOptionPane.showMessageDialog(null, "The Heating System is on "+ event.getActionCommand());
		}
	}
	private void addComponent(Component component, int row, int column, int width, int height){
		//create a specific function which can be re used for every component and specifies 
		// exactly where you want it placed and the size of which you want them
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		
		layout.setConstraints(component, constraints);
		container.add(component);
	}
}
