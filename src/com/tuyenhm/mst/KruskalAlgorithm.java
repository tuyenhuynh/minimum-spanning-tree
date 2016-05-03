/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.mst;

import java.util.Random;

/**
 *
 * @author tuyenhuynh
 */
public class KruskalAlgorithm {
    
    public static final int MAX = 100 ; 
    
    private Edge[] edges = new Edge[MAX]; 
    private int[] parent  = new int[MAX]; 
    private int edgeCount =5; 
    
    private int[][] mst  = new int[MAX][MAX]; 
    
    
    
    public int[][] findMST(Graph graph){
        
//        
//        edges[0]  = new Edge(0, 1, 3); 
//        edges[1]  = new Edge(0, 3, 1); 
//        edges[2]  = new Edge(1, 2, 2); 
//        edges[3]  = new Edge(2, 3, 5); 
//        edges[4]  = new Edge(3, 1, 4); 
//        
//        
//        edges[0]  = new Edge(0, 1, 1); 
//        edges[1]  = new Edge(0, 2, 3); 
//        edges[2]  = new Edge(0, 4, 4); 
//        edges[3]  = new Edge(2, 3, 2); 
//        edges[4]  = new Edge(3, 4, 1); 
//        edges[5]  = new Edge(1, 3, 5); 
//      

        this.edgeCount = graph.geteCount(); 
        this.edges = graph.getEdges(); 
        
        for (int i = 0 ; i < edgeCount ; ++i){
            parent[i] = i; 
        }
        
        sort(); 
        
        for(int i = 0 ; i < edgeCount ; ++i ) {
            //find root of u
            int u = findRoot(edges[i].getU()); 
            //find root of v
            int v = findRoot(edges[i].getV()); 
            
            //if u== v then the cycle created
            if(u != v) {
                int x = edges[i].getU(); 
                int y = edges[i].getV(); 
                
                //save the mst 
                mst[x][y] = mst[y][x] = edges[i].getWeight(); 
                
                union(u, v); 
                
                System.out.println("u: " + u + " v: " + v ); 
                
            }
        }
        return mst ; 
    }
    
    int findRoot(int x) {
        while( parent[x] != x) {
            x = parent[x]; 
        }
        return x; 
    }
    
    void union(int u, int v) {
        if(parent[u] == u && parent[v] ==v) {
            parent[v] =u; 
        }else if(parent[u] == u && parent[v] != v) {
            parent[u]  = v ; 
        }else if(parent[v] == v && parent[u] != u) {
            parent[v] = u ; 
        }else if(parent[u] != u && parent[v] != v) {
            int root1 = findRoot(u); 
            int root2 = findRoot(v);
            parent[root1] = root2; 
        }
    }
    
    private void sort() {
        for (int i = 0 ; i < edgeCount ; ++i) {
            for(int j = i + 1 ; j < edgeCount ; ++j) {
                if(edges[i].getWeight() > edges[j].getWeight() ){
                    Edge tmp = edges[i]; 
                    edges[i] = edges[j]; 
                    edges[j] = tmp; 
                }
            }
        }
    }
    
}
