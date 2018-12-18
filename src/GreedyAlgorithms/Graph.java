//$Id$
package GreedyAlgorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> {
	private List<Edge<T>> allEdges;
    private Map<Long,Vertex<T>> allVertex;
    boolean isDirected = false;
    public Graph(boolean isDirected){
        allEdges = new ArrayList<Edge<T>>();
        allVertex = new HashMap<Long,Vertex<T>>();
        this.isDirected = isDirected;
    }
    public void addEdge(long id1, long id2){
        addEdge(id1,id2,0);
    }
    public void addEdge(Vertex<T> v){
    	if(allVertex.containsKey(v)){
    		return;
    	}
    	allVertex.put(v.id, v);
		allEdges.addAll(v.getEdges());
    }
    public void addEdge(long id1, long id2,int weight){
    	Vertex<T> v1 = null;
    	if(allVertex.containsKey(id1)){
    		v1 = allVertex.get(id1);
    	}else{
    		v1 = new Vertex<T>(id1);
    		allVertex.put(id1, v1);
    	}
    	Vertex<T> v2 = null;
    	if(allVertex.containsKey(id2)){
    		v2 = allVertex.get(id2);
    	}else{
    		v2 = new Vertex<T>(id1);
    		allVertex.put(id2, v2);
    	}
    	Edge<T> e = new Edge<T>(v1, v2, isDirected, weight);
    	allEdges.add(e);
    	v1.addAdjacentVertex(e, v2);
	    if(!isDirected){
	    	v2.addAdjacentVertex(e, v1);
	    }
    }
    public List<Edge<T>> getAllEdges(){
        return allEdges;
    }
    
    public Collection<Vertex<T>> getAllVertex(){
        return allVertex.values();
    }
    public Vertex<T> getVertex(long id){
        return allVertex.get(id);
    }
    static class Vertex<T>{
    	long id;
    	T data;
    	List<Edge<T>> edges = new ArrayList<Edge<T>>();
    	List<Vertex<T>> adjacentVertices = new ArrayList<Vertex<T>>();
    	public Vertex(long id) {
    		this.id = id;
    	}
    	public long getId(){
    		return this.id;
    	}
    	public void setData(T data){
    		this.data = data;
    	}
    	public T getData(){
    		return this.data;
    	}
    	public void addAdjacentVertex(Edge<T> e,Vertex<T> v){
    		edges.add(e);
    		adjacentVertices.add(v);
    	}
    	public List<Edge<T>> getEdges(){
    		return edges;
    	}
    	public List<Vertex<T>> getAdjacentVertices(){
    		return adjacentVertices;
    	}
    	public int getDegree(){
            return edges.size();
        }
    	    
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (id ^ (id >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex<T> other = (Vertex<T>) obj;
            if (id != other.id)
                return false;
            return true;
        }

    }
    static class Edge<T>{
    	private int weight;
    	private boolean IS_DIRECTED = false;
    	private Vertex<T> vertex1;
    	private Vertex<T> vertex2;
    	public Edge(Vertex<T> vertex1,Vertex<T> vertex2){
    		this.vertex1 = vertex1;
    		this.vertex2 = vertex2;
    	}
    	public Edge(Vertex<T> vertex1,Vertex<T> vertex2,boolean isDirected, int weight){
    		this.vertex1 = vertex1;
    		this.vertex2 = vertex2;
    		this.IS_DIRECTED = isDirected;
    		this.weight = weight;
    	}
    	Vertex<T> getVertex1(){
            return vertex1;
        }
        
        Vertex<T> getVertex2(){
            return vertex2;
        }
        
        int getWeight(){
            return weight;
        }
        
        public boolean isDirected(){
            return IS_DIRECTED;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
            result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Edge<T> other = (Edge<T>) obj;
            if (vertex1 == null) {
                if (other.vertex1 != null)
                    return false;
            } else if (!vertex1.equals(other.vertex1))
                return false;
            if (vertex2 == null) {
                if (other.vertex2 != null)
                    return false;
            } else if (!vertex2.equals(other.vertex2))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Edge [isDirected=" + IS_DIRECTED + ", vertex1=" + vertex1
                    + ", vertex2=" + vertex2 + ", weight=" + weight + "]";
        }
    }
}
