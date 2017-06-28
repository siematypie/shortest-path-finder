package com.siematypie.models;

import java.util.HashMap;

public class Graph {
    private HashMap<String, Vertex> vertices;
    private HashMap<Integer, Edge> edges;
    private ShortestPath chosenShortestPath;

    public Graph() {
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }

    public boolean addConnected(String vertexLabel1, String vertexLabel2, int weight) {
        Vertex one = vertices.computeIfAbsent(vertexLabel1, Vertex::new);
        Vertex two = vertices.computeIfAbsent(vertexLabel2, Vertex::new);
        return addEdge(one, two, weight);
    }

    public boolean addEdge(Vertex one, Vertex two, int weight) {
        if (one.equals(two)) {
            return false;
        }

        //ensures the com.siematypie.models.Edge is not in the com.siematypie.models.Graph
        Edge e = new Edge(one, two, weight);
        if (edges.containsKey(e.hashCode())) {
            return false;
        } else if (!(vertices.containsValue(one) || vertices.containsValue(two))) {
            return false;
        }

        //and that the com.siematypie.models.Edge isn't already incident to one of the vertices
        else if (one.containsNeighbor(e) || two.containsNeighbor(e)) {
            return false;
        }

        edges.put(e.hashCode(), e);
        one.addNeighbor(e);
        two.addNeighbor(e);

        return true;
    }

    public boolean addVertexIfAbsent(Vertex vertex) {
        Vertex current = this.vertices.get(vertex.getLabel());
        if (current != null) {
            return false;
        }
        vertices.put(vertex.getLabel(), vertex);
        return true;
    }

    public void displayGraph() {
        for (Edge e : edges.values()) {
            System.out.println(e);
        }
    }

    public Vertex getVertex(String label) throws VertexNotFoundException {
        if (vertices.containsKey(label)) {
            return vertices.get(label);
        }
        throw new VertexNotFoundException("'" + label + "' vertex not found in graph");
    }

    public void resetGraph() {
        vertices.values().forEach(Vertex::reset);
        chosenShortestPath = null;
    }

    public ShortestPath getChosenShortestPath() {
        return chosenShortestPath;
    }

    public void setChosenShortestPath(ShortestPath chosenShortestPath) {
        this.chosenShortestPath = chosenShortestPath;
    }

    public HashMap<Integer, Edge> getEdges() {
        return edges;
    }

    public HashMap<String, Vertex> getVertices() {
        return vertices;
    }
}
