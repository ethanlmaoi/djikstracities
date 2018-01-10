import java.util.Iterator;

/*
 * file: ListWithIteratorInterface.java
 * author: Ethan Liao
 * class: CS141 - Programming and Problem Solving
 * 
 * assignment: program 4
 * date last modified: 11/29/2017
 * 
 * purpose: This program is a implementation of a graph data structure that plays with roads and cities.
 */
public interface ListWithIteratorInterface<T> 
{
	public void addEntry(T newEntry);
	public void removeEntry(T entry);
	public boolean isListEmpty();
	
	public Iterator<T> getIterator();
}
