package com.siematypie.engine;

import com.siematypie.models.*;

import java.util.HashSet;
import java.util.Set;

public class DijkstraAlgorithm {
    private Set<Vertex> unsettledNodes;
    private Vertex currentVertex;
    private Graph graph;

    public DijkstraAlgorithm(Graph graph) {
        this.graph = graph;
    }

    public ShortestPath calculateShortestPath(Vertex source, Vertex destination) throws PathNotFoundException {
        if (graph.getChosenShortestPath() != null) {
            graph.resetGraph();
        }
        source.setAsSource();
        unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0 && !destination.equals(currentVertex)) {
            currentVertex = getLowestDistanceVertex();
            for (Edge e : currentVertex) {
                updateNeighborDistance(e);
            }
        }

        ShortestPath sp = destination.getShortestPath();
        if (sp == null) {
            throw new PathNotFoundException("Path from '" + source + "' to '" + destination + "' doesn't exist");
        }
        this.graph.setChosenShortestPath(sp);
        return sp;
    }

    public ShortestPath calculateShortestPath(String sourceLabel, String destinationLabel) throws VertexNotFoundException, PathNotFoundException {
        return calculateShortestPath(graph.getVertex(sourceLabel), graph.getVertex(destinationLabel));
    }

    private Vertex getLowestDistanceVertex() {
        Vertex lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Vertex v : unsettledNodes) {
            int nodeDistance = v.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = v;
            }
        }
        lowestDistanceNode.visit();
        unsettledNodes.remove(currentVertex);
        return lowestDistanceNode;
    }

    private void updateNeighborDistance(Edge e) {
        Vertex neighbor = e.getNeighbor(currentVertex);
        if (!neighbor.getVisited()) {
            Integer newDistance = currentVertex.getDistance() + e.getWeight();
            if (neighbor.getDistance() > newDistance) {
                neighbor.setDistance(newDistance);
                ShortestPath shortestPath = new ShortestPath(currentVertex.getShortestPath());
                shortestPath.addEdge(e);
                neighbor.setShortestPath(shortestPath);
                unsettledNodes.add(neighbor);
            }
        }
    }
}
