import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;

/*
 * file: Vertex.java
 * author: Ethan Liao
 * class: CS141 - Programming and Problem Solving
 * 
 * assignment: program 4
 * date last modified: 11/29/2017
 * 
 * purpose: This program is a implementation of a graph data structure that plays with roads and cities.
 */

/*
 * See VertexInterface<T> for full description of the methods
 */
public class Vertex<T> implements VertexInterface<T> 
{
	private T label;
	private ListWithIteratorInterface<Edge> edgeList;
	private boolean visited;
	private VertexInterface<T> previousVertex;
	private double cost;
	
	public Vertex(T vertexLabel)
	{
		label = vertexLabel;
		edgeList = new LinkedListWithIterator<>();
		visited = false;
		previousVertex = null;
		cost = 0;
	}
	
	protected class Edge
	{
		private VertexInterface<T> vertex;
		private double weight;
		
		protected Edge(VertexInterface<T> endVertex, double edgeWeight)
		{
			vertex = endVertex;
			weight = edgeWeight;
		}
		
		protected VertexInterface<T> getEndVertex()
		{
			return vertex;
		}
		
		protected double getWeight()
		{
			return weight;
		}
	}
	
	public boolean connect(VertexInterface<T> endVertex, double edgeWeight)
	{
		boolean result = false;
		if (!this.equals(endVertex))
		{
			Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator();
			boolean duplicateEdge = false;
			
			while (!duplicateEdge && neighbors.hasNext())
			{
				VertexInterface<T> nextNeighbor = neighbors.next();
				if (endVertex.equals(nextNeighbor))
					duplicateEdge = true;
			}
			
			if (!duplicateEdge)
			{
				edgeList.addEntry(new Edge(endVertex, edgeWeight));
				result = true;
			}
		}
		return result;
	}
	
	public boolean connect(VertexInterface<T> endVertex)
	{
		return connect(endVertex,0);
	}
	
	public Iterator<VertexInterface<T>> getNeighborIterator()
	{
		return new NeighborIterator();
	}
	
	private class NeighborIterator implements Iterator<VertexInterface<T>>
	{
		private Iterator<Edge> edges;
		
		private NeighborIterator()
		{
			edges = edgeList.getIterator();
		}
		
		public boolean hasNext()
		{
			return edges.hasNext();
		}
		
		public VertexInterface<T> next()
		{
			VertexInterface<T> nextNeighbor = null;
			if (edges.hasNext())
			{
				Edge edgeToNextNeighbor = edges.next();
				nextNeighbor = edgeToNextNeighbor.getEndVertex();
			}
			else
				throw new NoSuchElementException();
			return nextNeighbor;
		}
		
		public void remove()
		{
			edges.remove();
		}
	}
	
	public VertexInterface<T> getUnvisitedNeighbor()
	{
		VertexInterface<T> result = null;
		
		Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
		while ((neighbors.hasNext()) && (result == null))
		{
			VertexInterface<T> nextNeighbor = neighbors.next();
			if (!nextNeighbor.isVisited())
				result = nextNeighbor;
		}
		return result;
	}
	
	public boolean hasNeighbor()
	{
		return !edgeList.isListEmpty();
	}
	
	public boolean equals(Object other)
	{
		boolean result;
		
		if ((other == null) || (getClass() != other.getClass()))
			result = false;
		else
		{
			Vertex<T> otherVertex = (Vertex<T>)other;
			result = label.equals(otherVertex.label);
		}
		return result;
	}

	public T getLabel() 
	{
		return label;
	}

	public void visit() 
	{
		visited = true;
	}

	public void unvisit() 
	{
		visited = false;
	}

	public boolean isVisited() 
	{
		return visited;
	}

	public Iterator<Double> getWeightIterator() 
	{
		return new WeightIterator();
	}
	
	private class WeightIterator implements Iterator<Double>
	{
		private Iterator<Edge> edges;
		
		private WeightIterator()
		{
			edges = edgeList.getIterator();
		}
		
		public boolean hasNext()
		{
			return edges.hasNext();
		}
		
		public Double next()
		{
			double nextWeight = 0.0;
			if (edges.hasNext())
			{
				double weightOfEdgeToNextNeighbor = edges.next().getWeight();
				nextWeight = weightOfEdgeToNextNeighbor;
			}
			else
				throw new NoSuchElementException();
			return nextWeight;
		}
	}

	public void setPredecessor(VertexInterface<T> predecessor) 
	{
		previousVertex = predecessor;
	}

	public VertexInterface<T> getPredecessor() 
	{
		return previousVertex;
	}

	public boolean hasPredecessor() 
	{
		return previousVertex != null;
	}

	public void setCost(double newCost) 
	{
		cost = newCost;
	}

	public double getCost() 
	{
		return cost;
	}
	
	
}
