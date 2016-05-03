/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.mst.view;

import com.tuyenhm.mst.Edge;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;
import com.tuyenhm.mst.Graph; 
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
/**
 *
 * @author tuyenhuynh
 */
public class NodesPanel extends JPanel{
    
    private static final Random random = new Random(); 
    private Graph  graph; 
    
    public NodesPanel() {
        
        Dimension d = new Dimension(900, 600); 
        setMaximumSize(d);
        setMinimumSize(d); 
        setPreferredSize(d);
        
    }
    
    public void drawGraph(Graph graph,  Graphics g) {
        Edge[] edges = graph.getEdges(); 
        int eCount = graph.geteCount(); 
        List<Point> points = graph.getNodesCoordinates(getWidth(), getHeight()); 
        
        
        for(int i = 0 ; i < points.size()  ; ++i) {
            
            g.setColor(Color.red);
            Point p = points.get(i); 
            g.fillOval(p.x -10, p.y -10, 20, 20);
            g.setColor(Color.black);
            g.drawString(""+ i, p.x , p.y ); 
        }
        
        g.setColor(Color.black);
        for(int i =  0; i < eCount ; ++i) {
            int u = edges[i].getU(); 
            int v = edges[i].getV(); 
            int weight = edges[i].getWeight(); 
            if(graph.isInMst(u, v)) {
                drawEdge(points.get(u), points.get(v), weight, g, 2, Color.blue); 
            }else {
                drawEdge(points.get(u), points.get(v),weight, g, (float)0.7, Color.black);
            }
        }
    }
    
    private void drawEdge(Point u, Point v, int weight, Graphics g,  float lineSize, Color color) {
        Graphics2D g2 = (Graphics2D)g; 
        g.setColor(color);
        g2.setStroke(new BasicStroke(lineSize));
        g2.drawLine(u.x, u.y, v.x, v.y);
        g2.drawString(""+weight, (float)((u.x + v.x)/2), (float)((v.y +u.y)/2));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if(graph != null) {
            drawGraph(graph, g); 
        }
    }
    
    public void setGraph(Graph graph) {
        this.graph = graph ; 
    }
}

