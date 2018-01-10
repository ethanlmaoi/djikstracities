import java.util.Dictionary;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.ArrayList;


/*
 * file: LinkedDictionary.java
 * author: Ethan Liao
 * class: CS141 - Programming and Problem Solving
 * 
 * assignment: program 4
 * date last modified: 11/29/2017
 * 
 * purpose: This program is a implementation of a graph data structure that plays with roads and cities.
 */
public class LinkedDictionary<T> extends Dictionary<T, VertexInterface<T>> implements DictionaryInterface<T, VertexInterface<T>>
{
	private LinkedHashMap<T,VertexInterface<T>> dictionary;
	private ArrayList<T> keyList;
	
	public LinkedDictionary()
	{
		super();
		dictionary = new LinkedHashMap<T,VertexInterface<T>>();
		keyList = new ArrayList<T>();
	}

	public Enumeration<VertexInterface<T>> elements() 
	{
		return null;
	}

	public VertexInterface<T> get(Object key)
	{
		return dictionary.get(key);
	}
	
	public VertexInterface<T> getValue(Object key)
	{
		return dictionary.get(key);
	}

	public boolean isEmpty()
	{
		return dictionary.isEmpty();
	}

	public Enumeration<T> keys() 
	{
		return null;
	}

	public VertexInterface<T> add(T key, VertexInterface<T> value)
	{
		keyList.add(key);
		return dictionary.put(key, value);
	}

	public VertexInterface<T> remove(Object key)
	{
		keyList.remove(key);
		return dictionary.remove(key);
	}

	public int size() 
	{
		return dictionary.size();
	}
	
	public int getSize()
	{
		return dictionary.size();
	}

	public VertexInterface<T> put(T key, VertexInterface<T> value) 
	{
		keyList.add(key);
		return dictionary.put(key, value);
	}
	
	public ArrayList<T> getKeys()
	{
		return keyList;
	}
	
	public void clear()
	{
		dictionary = new LinkedHashMap<T,VertexInterface<T>>();
	}
}
