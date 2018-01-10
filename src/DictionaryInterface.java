import java.util.ArrayList;
import java.util.Enumeration;


/*
 * file: DictionaryInterface.java
 * author: Ethan Liao
 * class: CS141 - Programming and Problem Solving
 * 
 * assignment: program 4
 * date last modified: 11/29/2017
 * 
 * purpose: This program is a implementation of a graph data structure that plays with roads and cities.
 */
public interface DictionaryInterface<T, VertexInterface>
{
	public Enumeration<VertexInterface> elements();
	public VertexInterface get(Object key);
	public boolean isEmpty();
	public Enumeration<T> keys();
	public VertexInterface add(T key, VertexInterface value);
	public VertexInterface remove(Object key);
	public int size();
	public int getSize();
	public void clear();
	public VertexInterface getValue(T begin);
	public ArrayList<T> getKeys();
}
