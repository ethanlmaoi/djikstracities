import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;


/*
 * file: Main.java
 * author: Ethan Liao
 * class: CS141 - Programming and Problem Solving
 * 
 * assignment: program 4
 * date last modified: 11/29/2017
 * 
 * purpose: This program is a implementation of a graph data structure that plays with roads and cities.
 */
public class Main 
{
	public static void main(String args[]) throws IOException
	{
		DirectedGraph<City> citiesGraph = new DirectedGraph<City>();  // directed graph for city
		
		// data from city.dat
		String[] lines = Files.readAllLines(new File("city.dat").toPath()).toArray(new String[0]);
		ArrayList<City> cityList = new ArrayList<City>();
		
		try
		{
			for (String line: lines) 
			{
			    int cityNumber = Integer.parseInt(line.substring(0, 2).replaceAll("^\\s+", "").replaceAll("\\s+$", ""));
			    String cityCode = line.substring(4,7).replaceAll("^\\s+", "").replaceAll("\\s+$", "");
			    String cityName = line.substring(8,25).replaceAll("^\\s+", "").replaceAll("\\s+$", "");
			    int population = Integer.parseInt(line.substring(30, 38).replaceAll("^\\s+", "").replaceAll("\\s+$", ""));
			    int elevation = Integer.parseInt(line.substring(39, 44).replaceAll("^\\s+", "").replaceAll("\\s+$", ""));
			    
			    cityList.add(new City(cityNumber, cityCode, cityName, population, elevation));
			}
		}
		catch (IndexOutOfBoundsException ex) 
		{
			// do nothing or ignore
		}
		
		// creation of vertices
		for (int i = 0; i < cityList.size(); i++)
		{
			citiesGraph.addVertex(cityList.get(i));
		}
		
		// data from road.dat
		lines = Files.readAllLines(new File("road.dat").toPath()).toArray(new String[0]);
		
		try
		{
			City cityOne = null;
			City cityTwo = null;
			
			for (String line: lines) 
			{
			    int cityOneNumber = Integer.parseInt(line.substring(0, 2).replaceAll("^\\s+", "").replaceAll("\\s+$", ""));
			    int cityTwoNumber = Integer.parseInt(line.substring(4, 7).replaceAll("^\\s+", "").replaceAll("\\s+$", ""));
			    int distance = Integer.parseInt(line.substring(9, 14).replaceAll("^\\s+", "").replaceAll("\\s+$", ""));
			    
			    for (int i = 0; i < cityList.size(); i++)
				{
					if (cityOneNumber == cityList.get(i).getCityNumber())
						cityOne = cityList.get(i);
					if (cityTwoNumber == cityList.get(i).getCityNumber())
						cityTwo = cityList.get(i);
				}
			    
			    citiesGraph.addEdge(cityOne, cityTwo, distance);
			}
		}
		catch (IndexOutOfBoundsException ex) 
		{
			// do nothing or ignore
		}
		
		System.out.println("\nCity Roads Program (4) by Ethan Liao");
		System.out.println("\n=====================================\n");
		System.out.println("Enter 'H' for help or for a list of commands!");
		
		Scanner input = new Scanner(System.in);
		boolean running = true;
		
		while (running)
		{
			System.out.print("\nCommand?: ");
			String option = input.nextLine().toUpperCase();
			
			if (option.equals("H"))
			{
				System.out.println("Q  Query the city information by entering the city codes.");
				System.out.println("D  Find the minimum distance between two cities.");
				System.out.println("I  Insert a road by entering two city codes and distance");
				System.out.println("R  Remove an existing road by entering two city codes.");
				System.out.println("H  Display this message");
				System.out.println("E  Exit");
			}
			else if (option.equals("I"))
			{
				System.out.print("City codes and distance: ");
				String cityCodesAndDistance = input.nextLine();
				
				City firstCity = null;
				City secondCity = null;
				String one = cityCodesAndDistance.substring(0,2);
				String two = cityCodesAndDistance.substring(3,5);
				int distance = Integer.parseInt(cityCodesAndDistance.substring(6));
				
				for (int i = 0; i < cityList.size(); i++)
				{
					if (one.equals(cityList.get(i).getCityCode()))
						firstCity = cityList.get(i);
					if (two.equals(cityList.get(i).getCityCode()))
						secondCity = cityList.get(i);
				}
				
				if (citiesGraph.hasEdge(firstCity, secondCity))
					System.out.println("There is already a road from " + firstCity.getCityName() + " to " + secondCity.getCityName() + ". Try removing it first!");
				else
				{
					citiesGraph.addEdge(firstCity, secondCity, distance);
					try {
						System.out.println("You have inserted a road from " + firstCity.getCityName() + " to " + secondCity.getCityName() + " with a distance of " + distance + ".");
					}
					catch (NullPointerException ex) {
						System.out.println("You have inserted a wrong city code / Wrong formatting!");
					}
				}
			}
			else if (option.equals("Q"))
			{
				System.out.print("City code: ");
				String cityCode = input.nextLine();
				
				String result = null;
				for (int i = 0; i < cityList.size(); i++)
				{
					if (cityList.get(i).getCityCode().equals(cityCode))
					{
						result = cityList.get(i).toString();
					}
				}
				if (result != null)
					System.out.println(result);
				else
					System.out.println("Invalid city code or city code could not be found!");
			}
			else if (option.equals("R"))
			{
				System.out.print("City code: ");
				String cityCodes = input.nextLine();
				
				City firstCity = null;
				City secondCity = null;
				String one = cityCodes.substring(0,2);
				String two = cityCodes.substring(3,5);
				
				for (int i = 0; i < cityList.size(); i++)
				{
					if (one.equals(cityList.get(i).getCityCode()))
						firstCity = cityList.get(i);
					if (two.equals(cityList.get(i).getCityCode()))
						secondCity = cityList.get(i);
				}
				
				if (citiesGraph.hasEdge(firstCity, secondCity))
				{
					citiesGraph.removeEdge(firstCity, secondCity);
					System.out.println("You have removed the road from " + firstCity.getCityName() + " to " + secondCity.getCityName() + ".");
				}
				else
					System.out.println("The road from " + firstCity.getCityName() + " and " + secondCity.getCityName() + " doesn't exist.");
			}
			else if (option.equals("D"))
			{
				System.out.print("City codes: ");
				String cityCodes = input.nextLine();
				
				City firstCity = null;
				City secondCity = null;
				String one = cityCodes.substring(0,2);
				String two = cityCodes.substring(3,5);
				
				for (int i = 0; i < cityList.size(); i++)
				{
					if (one.equals(cityList.get(i).getCityCode()))
						firstCity = cityList.get(i);
					if (two.equals(cityList.get(i).getCityCode()))
						secondCity = cityList.get(i);
				}
				
				System.out.println("The minimum distance between "  + firstCity.getCityName() + " and " + secondCity.getCityName() + " is " );//citiesGraph.dijikstra(firstCity, secondCity)););
			}
			else if (option.equals("E"))
			{
				System.exit(0);
			}
		}
	}
	
	
}

