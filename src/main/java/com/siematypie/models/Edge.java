package com.siematypie.models;

public class Edge {
    private Vertex one;
    private Vertex two;
    private Integer weight;

    public Edge(Vertex one, Vertex two, int weight) {
        if (weight < 1) {
            throw new IllegalArgumentException("Edge weight has to be an positive integer!");
        }
        this.one = (one.getLabel().compareTo(two.getLabel()) <= 0) ? one : two;
        this.two = (this.one == one) ? two : one;
        this.weight = weight;
    }

    public Vertex getNeighbor(Vertex current) {
        if (!(current.equals(one) || current.equals(two))) {
            return null;
        }
        return (current.equals(one)) ? two : one;
    }

    public Vertex getOne() {
        return one;
    }

    public Vertex getTwo() {
        return two;
    }

    public int getWeight() {
        return this.weight;
    }

    public String toString() {
        return connectionToString("----") + ", " + weight;
    }

    public String connectionToString(String delimiter) {
        return one.getLabel() + delimiter + two.getLabel();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Edge)) {
            return false;
        }

        Edge e = (Edge) other;
        return e.one.equals(this.one) && e.two.equals(this.two);
    }

    public int hashCode() {
        return (one.getLabel() + two.getLabel()).hashCode();
    }

    public String toDot() {
        return one + "--" + two + "[ label=\"" + this.weight + "\"];";
    }
}
