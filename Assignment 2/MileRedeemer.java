//Names: 
//Sean Mertens Z1630956
//William Dew Z1692069
//Andy Kung Z1624194
//MileRedeemer.java
/*Class MileRedeemer, encapsulates 
 the logic for redeeming mileage. Has 
 private instance variables for an array of 
 Destination objects, and an integer to represent 
 the remaining miles after the user's miles have been 
 redeemed. Initializes the remaining miles to 0.*/

import java.io.*;
import java.util.*;
import java.util.Scanner;
import static java.lang.System.exit;
import java.util.Arrays;

//////////////////////////////////////
//Class MileRedeemer                //
//Contains all the working methods  //
//that we call in the driver program//
//////////////////////////////////////
public class MileRedeemer
{
   //destinationArray is an array of destination objects
   private Destination [] destinationArray;
   //While miles is taken from the command line in our driver program,
   //we need to have a variable in MileRedeemer to work with. We will set this
   //equal to miles later   
   int remainingMiles;
   
   //readDestinations
   //returns nothing (hence the void declaration)
   //takes a scanner as an argument
   //also has a "throws" declaration since it utilizes exception handling
   //is used to parse in data from the input file and split it into parts
   public void readDestinations(Scanner fileScanner) throws IOException
   {     
     String destination;//a string variable
     
     //an ArrayList of Destination objects named destinationList
     //We will build this ArrayList as needed to store the destinations
     //that we will be sending back to the driver program
     ArrayList<Destination> destinationList = new ArrayList<Destination>();

     //an array of Strings named data, we will use this to hold all of our parsed
     //information
     String data[];
     
     //while loop, while the scanner is reading in input...
     while(fileScanner.hasNext())
     {
       destination = fileScanner.nextLine();

       //whenever the scanner runs into a semicolon or dash
       //it knows to split the information    
       data = destination.split("[;-]");
       
       //try/catch logic in case an error occurs, this parses the information into 
       //its relevant parts    
       try
       {
         destinationList.add(new Destination(data[0],
                            Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]),
                            Integer.parseInt(data[3]),
                            Integer.parseInt(data[4]),
                            Integer.parseInt(data[5])));
       }
       catch (Exception e)
       {
         //if something goes wrong with reading in the data, print out a message and quit    
         System.out.print("Exception occurred adding element to Destination array in MileRedeemer!");
         exit(1);
       }       
     }
     //typecast destinationArray to an array of Destination objects
     //the array will be the same length as that of destinationList  
     destinationArray = (Destination[]) destinationList.toArray(new Destination[destinationList.size()]);
     //use the MileageComparator class given to us to sort the destinations in ascending order
     //based on normal mileage
  Arrays.sort(destinationArray, new MileageComparator());
   }
   
   /*The second method getCityNames should loop through the array of Destination 
   objects and create an array of String objects from the city names. This array 
   can be sorted in ascending order and returned (to be printed out by the main 
   program).*/
   //(Taken from the assignment)
   
   //returns an array of strings
   public String[] getCityNames()
   {
      //cities is an array of string objects equal to the length of destinationArray
      String cities[] = new String[destinationArray.length];
      //iterate for the length of destinationArray
   for(int i = 0; i < destinationArray.length; i++)
   //use .getCityName to place the destination names in the array "cities"
   cities[i] = destinationArray[i].getCityName();
   //sort the array in ascending order
   Arrays.sort(cities);
   //return our array of strings
   return cities;
   }
   
   /*For redeemMiles(), miles is the total available miles, and month is the 
   desired month of departure. To avoid writing one huge method, you can (and 
   probably should) have the redeemMiles() method call some other methods to 
   accomplish subtasks as part of the larger overall algorithm. This method 
   should return an array of String objects containing descriptions of redeemed 
   tickets to be printed out by the main program. It should also save the miles 
   remaining after the tickets have been redeemed.
   Your algorithm should 1) try to get tickets that travel the farthest. 2) Use 
   supersaver whenever possible. 3) try to get as many different tickets as 
   possible; (at most 1 ticket is needed for 1 destination) 4) use the remaining 
   mileage for upgrade, if possible (try to upgrade the longest trip first).*/
   //(Taken from the assignment)
  
   //returns an array of string objects, takes 2 ints as arguments
   public String[] redeemMiles(int miles, int month)
   {
      //miles is taken as input from the user, we'll set it
      //equal to remainingMiles so we can save it and return it
      remainingMiles = miles;
      //create a new ArrayList to hold the tickets we purchase
      ArrayList<Destination> ticketList = new ArrayList<Destination>();
      //we loop through each destination available
   for(Destination dest : destinationArray)
      {
        //if the user wishes to depart during a period that enables them
        //to utilize the supersaver program...
        if(dest.getStartMonth() <= month && dest.getEndMonth() >= month)
        {
          //AND if they have enough miles...
          if(dest.getSuperSaverMiles() <= remainingMiles)
          {
            //purchase the ticket by deducting the mile cost
            //and adding the destination to our ArrayList
            remainingMiles = remainingMiles - dest.getSuperSaverMiles();
            ticketList.add(dest);
          }
        }
        //otherwise we are not able to use the supersaver program
        else
        {
          //we try to buy a normal ticket instead. If we can afford it...
          if(dest.getNormalMiles() <= remainingMiles)
          {
            //purchase the ticket by deducting the mile cost
            //and adding the destination to our ArrayList
            remainingMiles = remainingMiles - dest.getNormalMiles();
            ticketList.add(dest);
          }
        }
      }
      
      //now we declare a array of strings to hold our output that we'll be returning
      String[] ticketArray = new String[ticketList.size()];
   //this integer "i" will allow us to iterate through our array of strings
   //in order to print out relevant output
   int i=0;
      //for each object in our ArrayList (tickets we've purchased)
      for(Destination dest : ticketList)
      {
        //if we can afford to upgrade to first class
        if (dest.getUpgradeMiles() <= remainingMiles)
        {
          //upgrade the ticket and pay the miles
          remainingMiles = remainingMiles - dest.getUpgradeMiles();
          //take the current Destination object from our ArrayList and make an entry in our String array (first class)
          ticketArray[i]=("* A trip to " + dest.getCityName() + ", first class ");
    //iterate
    i++;
        } 
        //otherwise we can't afford to upgrade the ticket
        else
        {
          //take the current Destination object from our ArrayList and make an entry in our String array (economy class)
          ticketArray[i]=("* A trip to " + dest.getCityName() + ", economy class ");
    //iterate
    i++;
        }       
      } 
      //return our array of strings
      return ticketArray;
   } 
  
   /*The last method should return the saved remaining miles.*/
   //it returns an integer which we have set equal to the miles remaining
   public int getRemainingMiles()
   {
     return remainingMiles;
   }

   //New method for Assignment 2, returns the corresponding Destination
   //object for a given city name
   public Destination findDestination(String cityName)
   {
     for(int i=0; i<destinationArray.length; i++)
     {
       if(cityName.equals(destinationArray[i].getCityName()))
       {
         return destinationArray[i];
       }
     } //Blank Destination object that's returned if the above 
       //if statement isn't satisfied
       return new Destination(" ", 0, 0, 0, 0, 0);
   }
}