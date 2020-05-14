//Names: 
//Sean Mertens Z1630956
//William Dew Z1692069
//Andy Kung Z1624194
//Destination.java
/*Class Destination, encapsulates all related data members. 
Contains private instance variables and public accessor methods. 
A constructor that takes all of this 
information as arguments is also included.*/

///////////////////////////////////////////
//Class Destination                      //
//Contains data members and a constructor//
//Defines our custom "Destination" object//
///////////////////////////////////////////
public class Destination 
{
  //All related data members  
  private String cityName;
  private int normalMiles;
  private int superSaverMiles;
  private int upgradeMiles;
  private int startMonth;
  private int endMonth;
  
  //The constructor, takes a string to represent the location, and integers for the rest of the data
  public Destination(String cityName, int normalMiles, int superSaverMiles, int upgradeMiles, int startMonth, int endMonth)
  {
    this.cityName = cityName;
    this.normalMiles = normalMiles;
    this.superSaverMiles = superSaverMiles;
    this.upgradeMiles = upgradeMiles;
    this.startMonth = startMonth;
    this.endMonth = endMonth;
  }
  //Trivial get accessor methods
  public String getCityName()
  {
    return cityName;
  }
  public int getNormalMiles()
  {
    return normalMiles;
  }
  public int getSuperSaverMiles()
  {
    return superSaverMiles;
  }
  public int getUpgradeMiles()
  {
    return upgradeMiles;
  }
  public int getStartMonth()
  {
    return startMonth;
  }
  public int getEndMonth()
  {
    return endMonth;
  }
}


