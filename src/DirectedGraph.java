import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;


/*
 * file: DirectedGraph.java
 * author: Ethan Liao
 * class: CS141 - Programming and Problem Solving
 * 
 * assignment: program 4
 * date last modified: 11/29/2017
 * 
 * purpose: This program is a implementation of a graph data structure that plays with roads and cities.
 */

/*
 * See GraphInterface<T> for full description of the methods
 */
public class DirectedGraph<T> implements GraphInterface<T>
{
	private DictionaryInterface<T, VertexInterface<T>> vertices; // T, vertexinterface
	private int edgeCount;
	
	public DirectedGraph()
	{
		vertices = new LinkedDictionary<>();
		edgeCount = 0;
	}
	
	  /** Task: Adds a given vertex to the graph.
	   *  @param vertexLabel  an object that labels the new vertex and
	   *                      is distinct from the labels of current vertices
	   *  @return true if the vertex is added, or false if not */
	public boolean addVertex(T vertexLabel)
	{
		VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
		return addOutcome == null;
	}
	
	/** Task: Adds a weighted edge between two given distinct vertices that 
	   *        are currently in the graph. The desired edge must not already 
	   *        be in the graph. In a directed graph, the edge points 
	   *        toward the second vertex given.
	   *  @param begin  an object that labels the origin vertex of the edge 
	   *  @param end    an object, distinct from begin, that labels the end 
	   *                vertex of the edge
	   *  @param edgeWeight  the real value of the edge's weight
	   *  @return true if the edge is added, or false if not */
	public boolean addEdge(T begin, T end, double edgeWeight)
	{
		boolean result = false;
	
		VertexInterface<T> beginVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);
		
		if ((beginVertex != null) && (endVertex != null))
			result = beginVertex.connect(endVertex, edgeWeight);
		
		if (result)
			edgeCount++;
		
		return result;
	}
	
	/** Task: Adds an unweighted edge between two given distinct vertices 
	   *        that are currently in the graph. The desired edge must not
	   *        already be in the graph. In a directed graph, the edge points 
	   *        toward the second vertex given.
	   *  @param begin  an object that labels the origin vertex of the edge 
	   *  @param end    an object, distinct from begin, that labels the end 
	   *                vertex of the edge
	   *  @return true if the edge is added, or false if not */
	public boolean addEdge(T begin, T end)
	{
		return addEdge(begin, end, 0);
	}
	
	/** Task: removes an unweighted edge between two given distinct vertices 
	   *        that are currently in the graph.
	   *  @param begin  an object that labels the origin vertex of the edge 
	   *  @param end    an object, distinct from begin, that labels the end 
	   *                vertex of the edge
	   *  @return true if the edge is removed, or false if not */
	public boolean removeEdge(T begin, T end)
	{
		boolean removed = false;
		
		VertexInterface<T> beginVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);
		
		if ((beginVertex != null) && endVertex != null)
		{
			Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
			
			while (neighbors.hasNext())
			{
				VertexInterface<T> nextNeighbor = neighbors.next();
				if (endVertex.equals(nextNeighbor))
					neighbors.remove();
			}
		}
		return removed;
	}
	
	/** Task: Sees whether an edge exists between two given vertices.
	   *  @param begin  an object that labels the origin vertex of the edge 
	   *  @param end    an object that labels the end vertex of the edge
	   *  @return true if an edge exists */
	public boolean hasEdge(T begin, T end)
	{
		boolean found = false;
		
		VertexInterface<T> beginVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);
		
		if ((beginVertex != null) && endVertex != null)
		{
			Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
			
			while (!found && neighbors.hasNext())
			{
				VertexInterface<T> nextNeighbor = neighbors.next();
				if (endVertex.equals(nextNeighbor))
					found = true;
			}
		}
		return found;
	}
	
	/** Task: Sees whether the graph is empty.
	   *  @return true if the graph is empty */
	public boolean isEmpty()
	{
		return vertices.isEmpty();
	}
	
	/** Task: Removes all vertices and edges from the graph. */
	public void clear()
	{
		vertices.clear();
		edgeCount = 0;
	}
	
	/** Task: Gets the number of vertices in the graph.
	   *  @return the number of vertices in the graph */
	public int getNumberOfVertices()
	{
		return vertices.getSize();
	}
	
	/** Task: Gets the number of edges in the graph.
	   *  @return the number of edges in the graph */
	public int getNumberOfEdges()
	{
		return edgeCount;
	}
	
	/** Task: Finds the shortest path from one vertex to another
	   *  @return the shortest distance between the two vertices */
	public double dijikstra(VertexInterface<T> s, VertexInterface<T> e)
	{
		ArrayList<T> keyList = vertices.getKeys();
		ArrayList<VertexInterface<T>> vertexList = new ArrayList<VertexInterface<T>>();
		VertexInterface<T> start = null;
		VertexInterface<T> end = null;
		
		for (int i = 0; i < keyList.size(); i++)
		{
			vertexList.add(vertices.get(keyList.get(i)));
		}
		
		for (int i = 0; i < keyList.size(); i++)
		{
			if ((vertexList.get(i).getLabel()).equals(s))
			{
				start = vertexList.get(i);
			}
			if ((vertexList.get(i).getLabel()).equals(e))
			{
				end = vertexList.get(i);
			}
		}
		
		// Initialization
		for (int i = 0; i < vertexList.size(); i++)
		{
			vertexList.get(i).setCost(Double.POSITIVE_INFINITY);; // for every d[v] = infinity
			vertexList.get(i).setPredecessor(null); // for every d[v] = NIL or doesn't exist
		}
		
		start.setCost(0.0);
		start.setPredecessor(null);		
		
		while (!vertexList.isEmpty())
		{
			VertexInterface<T> minimumVertex = null;
			double minimumNumber = Double.POSITIVE_INFINITY;
			for (int i = 0; i < vertexList.size(); i++)
			{
				if (vertexList.get(i).getCost() < minimumNumber)
				{
					minimumVertex = vertexList.get(i);
					minimumNumber = vertexList.get(i).getCost();
				}
			}
			
			VertexInterface<T> u = minimumVertex;
			Iterator<VertexInterface<T>> neighborIterator = u.getNeighborIterator();
			Iterator<Double> weights = u.getWeightIterator();
			
			while (neighborIterator.hasNext())
			{
				VertexInterface<T> nextVertex = neighborIterator.next();
				double nextWeight = weights.next();
				if (u.getCost() + nextWeight < nextVertex.getCost())
				{
					nextVertex.setCost(u.getCost() + nextWeight);
					nextVertex.setPredecessor(u);
				}
			}
			u.visit();
		}
		return end.getCost();
	}
}
