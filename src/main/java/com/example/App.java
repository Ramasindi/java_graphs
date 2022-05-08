package com.example;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        List<Vertex<User>> usersNodes = new ArrayList<Vertex<User>>();
        List<Edge<User>> userRelations = new ArrayList<Edge<User>>();
    
        User muano = new User("Muano", "@muano");
        User washu = new User("Washu", "@washu");
        User candy = new User("Candy", "@candy");
        User thalu = new User("Thalu", "@thalu");

        Vertex<User> muanoNode = new Vertex<User>(muano);
        Vertex<User> washuNode = new Vertex<User>(washu);
        Vertex<User> candyNode = new Vertex<User>(candy);
        Vertex<User> thaluNode = new Vertex<User>(thalu);

        // The lower the cost the close the relationship
        Edge<User> muanoWashu = new Edge<User>(1, muanoNode, washuNode);
        Edge<User> washuCandy = new Edge<User>(2, washuNode, candyNode);
        Edge<User> candyThalu = new Edge<User>(3, candyNode, thaluNode);

        usersNodes.add(muanoNode);
        usersNodes.add(washuNode);
        usersNodes.add(candyNode);
        usersNodes.add(thaluNode);

        userRelations.add(muanoWashu);
        userRelations.add(washuCandy);
        userRelations.add(candyThalu);

        Graph<User> graph = new Graph<User>(usersNodes, userRelations);
        System.out.println(graph.toString());
    }
}
