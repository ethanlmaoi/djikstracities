import java.util.Iterator;

/*
 * file: VertexInterface.java
 * author: Ethan Liao
 * class: CS141 - Programming and Problem Solving
 * 
 * assignment: program 4
 * date last modified: 11/29/2017
 * 
 * purpose: This program is a implementation of a graph data structure that plays with roads and cities.
 */
public interface VertexInterface<T>
{
  /** Task: Gets the vertex label.
   *  @return the object that labels the vertex */
  public T getLabel();
  
  /** Task: Marks the vertex as visited. */
  public void visit();
  
  /** Task: Removes the vertex visited mark. */
  public void unvisit();
  
  /** Task: Sees whether the vertex is marked as visited.
   *  @return true if the vertex is visited */
  public boolean isVisited();
  
  /** Task: Connects this vertex and a given vertex with a weighted edge.
   *        The two vertices cannot be the same, and must not already
   *        have this edge between them. In a directed graph, the edge 
   *        points toward the given vertex.
   *  @param endVertex   a vertex in the graph that ends the edge
   *  @param edgeWeight  a real-valued edge weight, if any
   *  @return true if the edge is added, or false if not */
  public boolean connect(VertexInterface<T> endVertex, 
                         double edgeWeight);
                         
  /** Task: Connects this vertex and a given vertex with an unweighted 
   *        edge. The two vertices cannot be the same, and must not 
   *        already have this edge between them. In a directed graph, 
   *        the edge points toward the given vertex.
   *  @param endVertex   a vertex in the graph that ends the edge
   *  @return true if the edge is added, or false if not */
  public boolean connect(VertexInterface<T> endVertex);
  
  /** Task: Creates an iterator of this vertex's neighbors by following 
   *        all edges that begin at this vertex.
   *  @return an iterator of the neighboring vertices of this vertex */
  public Iterator<VertexInterface<T>> getNeighborIterator();

  /** Task: Creates an iterator of the weights of the edges to this 
   *        vertex's neighbors.
   *  @return an iterator of edge weights for edges to neighbors of this 
   *          vertex */
  public Iterator<Double> getWeightIterator();
  
  /** Task: Sees whether this vertex has at least one neighbor.
   *  @return true if the vertex has a neighbor */
  public boolean hasNeighbor();
  
  /** Task: Gets an unvisited neighbor, if any, of this vertex.
   *  @return either a vertex that is an unvisited neighbor or null
   *          if no such neighbor exists */
  public VertexInterface<T> getUnvisitedNeighbor();
  
  /** Task: Records the previous vertex on a path to this vertex.
   *  @param predecessor  the vertex previous to this one along a path */
  public void setPredecessor(VertexInterface<T> predecessor);
  
  /** Task: Gets the recorded predecessor of this vertex.
   *  @return either this vertex predecessor or null if no predecessor 
   *          was recorded */
  public VertexInterface<T> getPredecessor();
  
  /** Task: Sees whether a predecessor was recorded.
   *  @return true if a predecessor was recorded for this vertex */
  public boolean hasPredecessor();
  
  /** Task: Records the cost of a path to this vertex.
   *  @param newCost  the cost of the path */
  public void setCost(double newCost);
  
  /** Task: Gets the recorded cost of the path to this vertex.
   *  @return the cost of the path */
  public double getCost();
} // end VertexInterface
