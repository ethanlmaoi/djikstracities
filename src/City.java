
/*
 * file: City.java
 * author: Ethan Liao
 * class: CS141 - Programming and Problem Solving
 * 
 * assignment: program 4
 * date last modified: 11/29/2017
 * 
 * purpose: This program is a implementation of a graph data structure that plays with roads and cities.
 */
public class City 
{
	private int cityNumber;
	private String cityCode;
	private String cityName;
	private int population;
	private int elevation;
	
	public City(int cityNumber, String cityCode, String cityName, int population, int elevation)
	{
		this.cityNumber = cityNumber;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.population = population;
		this.elevation = elevation;
	}

	public int getCityNumber() {
		return cityNumber;
	}

	public void setCityNumber(int cityNumber) {
		this.cityNumber = cityNumber;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getElevation() {
		return elevation;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	
	public City getCity(int cityNumber)
	{
		City result = null;
		if (this.cityNumber == cityNumber)
		{
			result = this;
		}
		return result;
	}
	
	public String toString()
	{
		return "" + cityNumber + " " + cityCode + " " + cityName + " " + population + " " + elevation;
	}
}
