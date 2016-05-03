/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.mst;

/**
 *
 * @author tuyenhuynh
 */
public class PrimAlgorithm {
    
    public static final int MAX = 100 ; 
    
    private int vertexCount = 4; 
    
    private int[][] matrix ={{0, 3, 0, 1},
                             {3, 0, 2, 4},
                             {0, 2, 0, 5},
                             {1, 4, 5, 0}
                             }; 
    
    private int[][] mst   = new int[MAX][MAX]; 
    
    boolean free[] = new boolean [MAX]; 
    
    int[] parent = new int[MAX]; 
    
    int[] key = new int[MAX]; 
    
    public int[][] prim(int start, PrimGraph graph) {
        vertexCount = graph.getvCount(); 
        matrix = graph.getMatrix(); 
        mst = new int[vertexCount][vertexCount];
        
        for(int i = 0 ; i < vertexCount ; ++i) {
            parent[i] = -1 ; 
            free[i] = true ; 
            key[i] = Integer.MAX_VALUE; 
        }
        
        key[start] = 0; 
        
        for(int i = 0 ; i < vertexCount; ++i) {
            int u = extractMin(); 
            free[u] = false  ;
            
            for(int v= 0 ; v < vertexCount ;++v) {
                if(free[v] && matrix[u][v] != 0 && matrix[u][v] < key[v]) {
                    key[v] = matrix[u][v]; 
                    parent[v] = u ; 
                }
            }
        }
        
        for(int i = 0 ; i < vertexCount ; ++i) {
            if(i != start) {
                mst[parent[i]][i] = mst[i][parent[i]] = matrix[parent[i]][i];
                System.out.println(i +":" + parent[i] +":" + mst[parent[i]][i]);
            }
        }
        return mst; 
    }
    
    
    private int extractMin() {
        int min = Integer.MAX_VALUE, u = 0; 
        for( int i =0 ; i < vertexCount ; ++i) {
            if(free[i] && key[i]  < min) {
                min = key[i]; 
                u = i ; 
            }
        }
        return u ; 
    }
   
}
