# shortest-path-finder
This maven project contains my implementation of undirected graph structure and dijkstra algorithm in java with javafx gui and graphviz library for graph visualization. Example output:

![example graph](https://raw.githubusercontent.com/siematypie/shortest-path-finder/master/example1.png)

## creating executable jar
For creating executacle jar file this project is using awesome library called JavaFX Maven Plugin. Basically, all you have to do is to open project folder in terminal and call `mvn jfx:jar`. The jar-file will be placed at `target/jfx/app`. For more info, check out plugin's github page:
**[https://github.com/javafx-maven-plugin/javafx-maven-plugin](https://github.com/javafx-maven-plugin/javafx-maven-plugin)**

## using the program
To use your own graph, you have to provide csv in proper format. The graph has to be undirected, weighted and every node must have at least one connection with some other node.
Row in csv is equal to edge in graph. First and second cell should contain vertex labels, third cell should have edge weight positive integer. Your csv file should have comma as delimiter. 
Example: Csv file with one line `A,B,100` would result in following graph:

![example graph2](https://raw.githubusercontent.com/siematypie/shortest-path-finder/master/example.png)

