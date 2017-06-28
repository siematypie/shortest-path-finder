package com.siematypie.models;

import java.util.ArrayList;
import java.util.Iterator;

public class Vertex implements Iterable<Edge>{
    private ArrayList<Edge> neighborhood;
    private String label;
    private Integer distance;
    private ShortestPath shortestPath;
    private Boolean visited;

    public Vertex(String label) {
        this.label = label;
        this.distance = Integer.MAX_VALUE;
        this.neighborhood = new ArrayList<>();
        this.visited = false;
    }

    public String getLabel(){
        return this.label;
    }

    @Override
    public Iterator<Edge> iterator() {
        return neighborhood.iterator();
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return label.equals(vertex.label);
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    public ArrayList<Edge> getNeighbors(){
        return new ArrayList<>(this.neighborhood);
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public boolean containsNeighbor(Edge e) {
        return this.neighborhood.contains(e);
    }

    public void addNeighbor(Edge e){
        if (!neighborhood.contains(e)){
            this.neighborhood.add(e);
        }
    }

    public void setAsSource(){
        visited = true;
        distance = 0;
        shortestPath = new ShortestPath(this);
    }

    public ShortestPath getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(ShortestPath shortestPath) {
        shortestPath.setWeight(this.distance);
        shortestPath.setDestination(this);
        this.shortestPath = shortestPath;
    }

    public void reset(){
        this.distance = Integer.MAX_VALUE;
        this.visited = false;
        this.shortestPath = null;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void visit(){
        visited = true;
    }

}
