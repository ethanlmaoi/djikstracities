import java.util.Iterator;
import java.util.LinkedList;

/*
 * file: LinkedListWithIterator.java
 * author: Ethan Liao
 * class: CS141 - Programming and Problem Solving
 * 
 * assignment: program 4
 * date last modified: 11/29/2017
 * 
 * purpose: This program is a implementation of a graph data structure that plays with roads and cities.
 */
public class LinkedListWithIterator<T> extends LinkedList<T> implements ListWithIteratorInterface<T>
{   
    public LinkedListWithIterator()
    {
        super();
    }
    
    public void addEntry(T newEntry)
    {
    	add(newEntry);
    }
    
    public void removeEntry(T entry)
    {
    	remove(entry);
    }
    
    public boolean isListEmpty()
    {
    	return isEmpty();
    }
    
    public Iterator<T> getIterator()
    {
    	return iterator();
    }
}