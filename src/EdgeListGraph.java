import java.util.*;
import java.util.LinkedList;

public class EdgeListGraph {
     class Vertex {
          String element;
          ArrayList<Edge> AdjacentEdges = new ArrayList<>();

          int key;

          Vertex() {
              vertices.add(Vertex.this);
          }

          Vertex(String element) {
              vertices.add(Vertex.this);
              this.element = element;
          }
     }

     class Edge {
         Vertex first;
         Vertex second;
         Integer weight;

         Edge(Vertex first, Vertex second) {
             this.first = first;
             this.second = second;

             edges.add(Edge.this);
         }
         Edge(Vertex first, Vertex second, int weight) {
             this.first = first;
             this.second = second;
             this.weight = weight;

             edges.add(Edge.this);

         }
     }


     private ArrayList<Edge> edges = new ArrayList<>();
     private ArrayList<Vertex> vertices = new ArrayList<>();

     private void insertVertex(String element) {
         Vertex vertex = new Vertex(element);
     }

     public void insertEdge(Vertex v, Vertex w) {
         Edge edge = new Edge(v, w);

         v.AdjacentEdges.add(edge);
         w.AdjacentEdges.add(edge);

     }

     public void insertEdge(Vertex v, Vertex w, Integer weight) {
         Edge edge = new Edge(v, w, weight);

         v.AdjacentEdges.add(edge);
         w.AdjacentEdges.add(edge);
     }

     public void removeEdge(Edge edge) {
         edge.first.AdjacentEdges.remove(edge);
         edge.second.AdjacentEdges.remove(edge);
         edges.remove(edge);
     }

     public void removeVertex(Vertex vertex) {
         for (int i = 0; i < vertex.AdjacentEdges.size(); i++) {
             if (vertex.AdjacentEdges.get(i).first == vertex) {
                 vertex.AdjacentEdges.get(i).second.AdjacentEdges.remove(vertex.AdjacentEdges.get(i));
             }
             else {
                 vertex.AdjacentEdges.get(i).first.AdjacentEdges.remove(vertex.AdjacentEdges.get(i));
             }
             edges.remove(vertex.AdjacentEdges.get(i));
         }
         vertices.remove(vertex);
     }

     public boolean areAdjacent(Vertex first, Vertex second) {
         for (int i = 0; i < second.AdjacentEdges.size(); i++) {
             if (first.AdjacentEdges.contains(second.AdjacentEdges.get(i))) {
                 return true;
             }
         }
         return false;
     }

     public Queue<Vertex> DFS() {
         Queue<Vertex> visited = new LinkedList<>();
         return DFS(vertices.get(0), visited);
     }

     public Queue<Vertex> DFS(Vertex current) {
         Queue<Vertex> visited = new LinkedList<>();
         return DFS(current, visited);
     }

     private Queue<Vertex> DFS(Vertex current, Queue<Vertex> visited) {
         visited.add(current);
         for(Edge temp:current.AdjacentEdges) {
             if (temp.first == current && !(visited.contains(temp.second))) {
                 DFS(temp.second, visited);
             }
             else if (!(visited.contains(temp.first))){
                 DFS(temp.first, visited);
             }
         }
         return visited;
     }

     public void BFS(Vertex root) {

         Queue<Vertex> queue = new LinkedList<>();
         Set<Vertex> black = new HashSet<>();
         Set<Vertex> grey = new HashSet<>();

         queue.add(root);
         grey.add(root);

         root.key = 0;

         while(!queue.isEmpty()) {
             Vertex current = queue.peek();
             for (Edge temp: current.AdjacentEdges) {
                 if (temp.first == current && !(grey.contains(temp.second) || black.contains(temp.second))) {
                     temp.second.key = current.key + 1;
                     queue.add(temp.second);
                     grey.add(temp.second);
                 }
                 else if (!(grey.contains(temp.first) || black.contains(temp.first))){
                     temp.first.key = current.key + 1;
                     queue.add(temp.first);
                     grey.add(temp.first);
                 }
             }
             queue.remove(current);
             grey.remove(current);
             black.add(current);
         }
     }

     /*public EdgeListGraph Prim() {

     }*/



     public static void main(String[] args) {
         EdgeListGraph graph = new EdgeListGraph();
         /*graph.insertVertex("A");
         graph.insertVertex("B");
         graph.insertVertex("C");
         graph.insertVertex("D");
         graph.insertVertex("E");
         graph.insertVertex("F");
         graph.insertVertex("G");
         graph.insertVertex("H");
         graph.insertVertex("I");

         graph.insertEdge(graph.vertices.get(0), graph.vertices.get(1));
         graph.insertEdge(graph.vertices.get(0), graph.vertices.get(2));
         graph.insertEdge(graph.vertices.get(0), graph.vertices.get(3));
         graph.insertEdge(graph.vertices.get(0), graph.vertices.get(4));
         graph.insertEdge(graph.vertices.get(1), graph.vertices.get(5));
         graph.insertEdge(graph.vertices.get(3), graph.vertices.get(6));
         graph.insertEdge(graph.vertices.get(5), graph.vertices.get(7));
         graph.insertEdge(graph.vertices.get(6), graph.vertices.get(8));*/

     }
}