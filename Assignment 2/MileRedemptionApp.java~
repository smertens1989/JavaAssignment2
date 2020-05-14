//DEAR SID: For some reason, this program REFUSES to display correctly at home or on any of the lab computers
//UNLESS you resize the frame. Simply run the program and resize it a little and it has perfect functionality,
//otherwise it displays incorrectly. I have no idea why it does this, I'd love to hear your input. -Sean

//Names: 
//Sean Mertens Z1630956
//William Dew Z1692069
//Andy Kung Z1624194
//MileRedemptionApp.java
/*Main class MileRedemptionApp, has the main() method which 
 contains the loop for user interaction. Gets the command
 line argument, use the filename to create the Scanner object, creates 
 an instance of MileRedeemer, tells it to read in the destination 
 data, gets the city names and print them out, reads the data input 
 from the user, uses redeemMiles() to get the list of tickets that may be 
 redeemed, and getRemainingMiles() to get the miles remaining.*/

//import statements, pretty self-explanatory
import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.*;
import javax.swing.JList;
import java.lang.String;
import java.lang.StringBuilder;

//////////////////////////////
//class MileRedemptionApp   //
//It's our main driver prog //
//////////////////////////////
public class MileRedemptionApp extends JFrame
{
  static MileRedeemer m; //MileRedeemer object that we use our methods on
  Destination dest; //Destination object that we use our get/set accessors on
  //an array of string objects to populate the Spinner, the user sees these
  String [] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" } ;
  //vars all used for the Spinner to function correctly
  int month = 0;
  String current = null;
  String selectedMonth = null;
  String [] answer = null;
  
  public static void main(String [] args) throws IOException
  {
    Scanner fileScanner;//Scanner for reading in miles.txt
    
    if (args.length > 0)//If an argument of any length is detected
    {
      fileScanner = new Scanner(new File(args[0]));//Read in miles.txt as an argument
      m = new MileRedeemer();//Create m, a new MileRedeemer object
      m.readDestinations(fileScanner);//Use the methods we've coded in MileRedeemer.java
    }   
    new MileRedemptionApp();//no-arg constructor
  }
    public MileRedemptionApp()
    {
      super( "Assignment 2" );//label for the GUI window
      
      this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      this.setSize( 700, 400 ); // set frame size
      this.setVisible( true ); // display frame
      this.setResizable( true ); // enable resizing
          
      setLayout( new GridLayout() ); // sets the grid layout
      // set the border title for the first JPanel
      TitledBorder title1 = new TitledBorder("List of Destination Cities");
      // set the border title for the second JPanel
      TitledBorder title2 = new TitledBorder("Redeem Tickets");
     
      JPanel pane1 = new JPanel();// create our first JPanel
      pane1.setLayout(new BorderLayout(6,6));
      
      JPanel pane2 = new JPanel();// create our second JPanel
      pane2.setLayout(new BorderLayout(6,6));
      pane1.setBorder(title1);// add the first border to the first panel
      pane2.setBorder(title2);// add the second border to the second panel
      add(pane1);// add the first JPanel to the JFrame
      add(pane2);// add the second JPanel to the JFrame
      Color newColor1 = new Color (93, 152, 180);// creates pane1's color
      Color newColor2 = new Color (217, 126, 80);// creates pane2's color
      pane1.setBackground(newColor1);// set the background of the first panel to newColor1
      pane2.setBackground(newColor2);// set the background of the second panel to newColor2
      
      //The array of strings returned by the MileRedeemer method getCityNames()can be used to populate the JList.  
      String [] data;
      data = m.getCityNames();
      final JList<String> cityList = new JList<String>(data); // create a JList
      cityList.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
      pane1.add(cityList, BorderLayout.CENTER);// add our JList to the nested JPanel
      
      //CODE FOR THE JTEXTFIELDS
      
      JPanel panelb = new JPanel();// create our second nested JPanel
      panelb.setLayout(new GridLayout(4,4));// set the nested JPanel's layout to BoxLayout
      //panelb.setMinimumSize(new Dimension(330, 156));
      //panelb.setPreferredSize(new Dimension(330, 156));
      //panelb.setMaximumSize(new Dimension(Short.MAX_VALUE,
                                      //Short.MAX_VALUE));
    
      final JTextField nMiles = new JTextField(8);//first display field
      nMiles.setEditable(false);//can't be edited
      final JTextField uMiles = new JTextField(8);//second
      uMiles.setEditable(false);//can't be edited
      final JTextField ssMiles = new JTextField(8);//third
      ssMiles.setEditable(false);//can't be edited
      final JTextField dispMonth = new JTextField(15);//final
      dispMonth.setEditable(false);//can't be edited
      panelb.add(new JLabel("Required Miles "));//label
      panelb.add(nMiles);//display output
      panelb.add(new JLabel("Miles for Upgrading "));
      panelb.add(uMiles);
      panelb.add(new JLabel("Miles for SuperSaver "));
      panelb.add(ssMiles);
      panelb.add(new JLabel("Months for SuperSaver "));
      panelb.add(dispMonth);
      
      pane1.add(panelb, BorderLayout.SOUTH);// add our nested JPanel to the first JPanel
      
      JPanel panelc = new JPanel(); // Create out third next JPanel
      panelc.setLayout(new GridLayout(3,1)); //set the nest JPanel's layout to gridlayout
      //panelc.setMinimumSize(new Dimension(330, 156));
      //panelc.setPreferredSize(new Dimension(330, 156));
      //panelc.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
      
      //Nested panels for Panelc
      final JTextField yourMiles = new JTextField(10);
      yourMiles.setEditable(true);
      JPanel panelc1 = new JPanel(); //Accumlated Miles panel
      panelc1.setLayout(new FlowLayout(FlowLayout.CENTER));
      
      SpinnerModel model = new SpinnerListModel(months);//create a spinner model
      final JSpinner spinner = new JSpinner(model);//create a spinner
      JPanel panelc2 = new JPanel(); //Months spinnner panel
      panelc2.setLayout(new FlowLayout(FlowLayout.CENTER));
      
      JButton button1 = new JButton("Redeem Tickets");//create a new button
      JPanel panelc3 = new JPanel(); //Button panel
      panelc3.setLayout(new FlowLayout(FlowLayout.CENTER));
      
      //add the panels 
      panelc.add(panelc1);
      panelc.add(panelc2);
      panelc.add(panelc3);
            
      panelc1.add(new JLabel("Your Accumlated Miles: ")); //Label for text field
      panelc1.add(yourMiles);//add the JTextField
      panelc2.add(new JLabel("Months" ));//label for the spinner
      panelc2.add(spinner);//add the spinner
      panelc3.add(button1);//add the button
      
      pane2.add(panelc, BorderLayout.NORTH);//adding panelc to pane2
      
      final JTextArea paneld = new JTextArea();//create our JTextArea for information on flights
      pane2.add(paneld, BorderLayout.CENTER);
      
      JPanel panele = new JPanel(); //create our fourth JPanel
      panele.setLayout(new FlowLayout(FlowLayout.CENTER));
      final JTextField remaining = new JTextField(10);//create the last JTextField
      remaining.setEditable(false);//can't be edited
      panele.add(new JLabel("Your Remaining Miles: "));//label for the last JTextField
      panele.add(remaining);//add the JTextField
      
      pane2.add(panele, BorderLayout.SOUTH); //setting panele

      //Listener to tell when the user has selected a destination
      //from the list
      ListSelectionListener lsl = new ListSelectionListener()
      {
        public void valueChanged(ListSelectionEvent e)
        {
          dest = m.findDestination(cityList.getSelectedValue());
          StringBuilder mString = new StringBuilder();
          mString.append(months[dest.getStartMonth()-1]);
          mString.append(" to ");
          mString.append(months[dest.getEndMonth()-1]);
          String current = mString.toString();
           
          nMiles.setText(Integer.toString(dest.getNormalMiles()));//normal miles
          uMiles.setText(Integer.toString(dest.getUpgradeMiles()));//u miles
          ssMiles.setText(Integer.toString(dest.getSuperSaverMiles()));//ss miles
          dispMonth.setText(current);//months
        }
      };
      cityList.addListSelectionListener(lsl);
    //button listener
    button1.addActionListener (new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if (yourMiles.getText() == "");//if nothing is typed
        {
          paneld.setText("Please input your accumulated miles \n");//display error message
        }
        
        selectedMonth = (String)spinner.getValue();//selectedMonth is whatever's selected from the spinner
        for (int i = 0; i < 12; i++)//for all 12 months...
        {
          if (selectedMonth.equals(months[i]))//set an integer "month" equal to a numerical value from 1-12
          {
            month = i;
          }
          answer = m.redeemMiles(Integer.valueOf(yourMiles.getText()),month);//we use the redeemMiles method on our 
                                                                             //MileRedeemer object
          paneld.setText("Your accumulated miles can be used for:");//output message
          paneld.append("\n");//newline
          for(int j=0; j<answer.length; j++)//for each destination, create a new line and output
          {
            paneld.append("\n");
            paneld.append(answer[j]);
          }
          remaining.setText(Integer.toString(m.getRemainingMiles()));//display the remaining miles
        }
      }
    });
    }
}
    
     