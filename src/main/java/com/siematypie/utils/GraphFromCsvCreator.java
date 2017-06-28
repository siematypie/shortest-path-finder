package com.siematypie.utils;

import au.com.bytecode.opencsv.CSVReader;
import com.siematypie.models.Graph;

import java.io.*;

public class GraphFromCsvCreator {

    public GraphFromCsvCreator(){
    }

    public Graph createFromResources(String path) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(path);
        return graphFromInputStream(is);
    }

    public Graph graphFromInputStream(InputStream is) throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(is));
        String [] nextLine;
        Graph graph = new Graph();
        while ((nextLine = reader.readNext()) != null) {
            graph.addConnected(nextLine[0], nextLine[1], Integer.parseInt(nextLine[2]));
        }
        return graph;
    }
}
