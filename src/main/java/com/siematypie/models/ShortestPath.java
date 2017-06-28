package com.siematypie.models;

import java.util.*;

public class ShortestPath extends AbstractCollection<Edge> {
    private List<Edge> pathList;
    private Vertex source;
    private Vertex destination;
    private Integer weight;

    public ShortestPath(Vertex startingVertex) {
        pathList = new LinkedList<>();
        source = startingVertex;
        destination = startingVertex;
        weight = 0;
    }

    public ShortestPath(ShortestPath other) {
        this.pathList = new LinkedList<>(other.pathList);
        this.source = other.source;
        this.destination = other.destination;
        this.weight = other.weight;
    }

    public void addEdge(Edge edge) {
        pathList.add(edge);
    }

    @Override
    public Iterator<Edge> iterator() {
        return pathList.iterator();
    }

    @Override
    public int size() {
        return pathList.size();
    }

    @Override
    public String toString() {
        return "Shortest Path from " + source + " to " + destination + ":\n" + pathListToString(" --> ") +
                "\nTotal Cost: " + weight;
    }

    public String pathListToString(String delimiter) {
        StringJoiner sj = new StringJoiner(delimiter);
        Vertex current = source;
        sj.add(source.getLabel());
        for (Edge e : pathList) {
            current = e.getNeighbor(current);
            sj.add(current.getLabel());
        }

        return sj.toString();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer distance) {
        this.weight = distance;
    }

    public void setDestination(Vertex destination) {
        this.destination = destination;
    }
}
