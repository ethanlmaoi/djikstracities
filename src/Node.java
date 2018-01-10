/*
 * file: Node.java
 * author: Ethan Liao
 * class: CS141 - Programming and Problem Solving
 * 
 * assignment: program 4
 * date last modified: 11/29/2017
 * 
 * purpose: This program is a implementation of a graph data structure that plays with roads and cities.
 */
public class Node<T> 
	{
		private T data;
	    private Node<T> next;
	        
	    /**
	    * This constructor will construct a Node object with a data inside, but no
	    * reference to the next Node.
	    */
	    public Node(T dataPortion)
	    {
	        this(dataPortion,null);
	    }
	    
	    /**
	    * This constructor will construct a Node Object with both the data and the
	    * reference to the nextNode.
	    */
	    public Node(T dataPortion, Node<T> nextNode)
	    {
	        data = dataPortion;
	        next = nextNode;
	    }
	    
	    /**
	    * This method will return the data inside of the Node.
	    */
	    public T getData()
	    {
	        return data;
	    }
	    
	    /**
	    * This method will set the data (or replace the data) inside of the Node.
	    */
	    public void setData(T dataPortion)
	    {
	        data = dataPortion;
	    }
	    
	    /**
	    * This method will return a Node which is a reference to the next Node from the current one.
	    */
	    public Node<T> getNext()
	    {
	        return next;
	    }
	    
	    /**
	    * This method will set the next Node (or replace the next Node).
	    */
	    public void setNext(Node<T> nextNode)
	    {
	        next = nextNode;
	    }
	    
	    /**
	    * This will tell the console what to print out for a Node object.
	    */
	    public String toString()
	    {
	        return data + " " + next;
	    }
	}