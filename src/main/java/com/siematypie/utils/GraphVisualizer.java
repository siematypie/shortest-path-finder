package com.siematypie.utils;

import com.siematypie.models.Edge;
import com.siematypie.models.Graph;
import com.siematypie.models.ShortestPath;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.parse.Parser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GraphVisualizer {
    public static BufferedImage getImage(Graph graph) throws IOException {
       return getImage(graph, 1000, Format.PNG);
    }

    public static BufferedImage getImage(Graph graph, Integer width, Format format) throws IOException {
        String dot = graphToDot(graph);
        return Graphviz.fromGraph(Parser.read(dot)).width(1000).render(Format.PNG).toImage();
    }

    public static void saveImage(Graph graph, String path) throws IOException {
        String dot = graphToDot(graph);
        Graphviz.fromGraph(Parser.read(dot)).width(1000).render(Format.PNG).toFile(new File(path));
    }

    private static String graphToDot(Graph g){
        List<Edge> graphEdges = new LinkedList<>(g.getEdges().values());
        StringBuilder sb = new StringBuilder();
        sb.append("graph G{ rankdir=RL;");
        ShortestPath sp = g.getChosenShortestPath();
        if (sp != null){
            sb.append(shortestPathToSubgraphDot(sp));
            graphEdges.removeAll(sp);
        }
        for(Edge e: graphEdges){
            sb.append(edgeToDot(e));
        }
        sb.append("}");
        return sb.toString();
    }

    private static String shortestPathToSubgraphDot(ShortestPath sp) {
        StringBuilder sb = new StringBuilder();
        sb.append("Subgraph shortestPath{ node[color=green, penwidth=3]; edge[color=\"green\", penwidth=5];");
        for(Edge e: sp){
            sb.append(edgeToDot(e));
        }
        sb.append("}");
        return sb.toString();
    }

    private static String edgeToDot(Edge e){
        return e.getOne().getLabel() + "--" + e.getTwo().getLabel() + "[ label=\"" + e.getWeight() + "\"];";
    }
}
