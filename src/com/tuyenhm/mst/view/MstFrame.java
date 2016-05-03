/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.mst.view;

import com.tuyenhm.mst.Graph;
import com.tuyenhm.mst.PrimGraph;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author tuyenhuynh
 */
public class MstFrame extends JFrame{
    
    private NodesPanel nodesPanel; 
    private JPanel headerPanel  = new JPanel(); 
    private JRadioButton kruskalRButton = new JRadioButton("Kruskal Algorithm");
    private JRadioButton primeRButton = new JRadioButton("Prim Algorithm"); 
    private JLabel edgeLabel = new JLabel("Edge: "); 
    private JTextField edgeField = new JTextField(5);
    private JLabel vLabel = new JLabel("Vertex: "); 
    private JTextField vField = new JTextField(5); 
    private JButton btnApply = new JButton("Apply"); 
    
    private int algorithm ; 
    
    private Graph graph; 
    
    public MstFrame() {
        
        initialComponents();
        
        kruskalRButton.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1) {
                    algorithm = 1 ; 
                }
            }
        });
        
        primeRButton.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1) {
                    algorithm = 2 ; 
                }
            }
        });
        
        btnApply.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int vCount, eCount;
                try{
                    vCount = Integer.parseInt(vField.getText()); 
                    eCount = Integer.parseInt(edgeField.getText()); 
                    boolean error = false ; 
                    if(vCount >100) {
                        JOptionPane.showMessageDialog(null, "Number of vertexs should not > 100");
                        error = true; 
                    }else if(eCount < vCount ) {
                        JOptionPane.showMessageDialog(null, "Number of edges should be >= number of vertext");
                        error = true; 
                    } else if(vCount <=2 ) {
                        JOptionPane.showMessageDialog(null, "Number of vertexs should >= 2");
                        error = true; 
                    }else if(eCount > (vCount*(vCount-1)/2)){
                        JOptionPane.showMessageDialog(null, "Error: eCount > (vCount*(vCount-1)/2)"); 
                        error = true; 
                    }
                    if(!error) {
                        if(algorithm == 1) {
                            graph = new Graph(vCount, eCount); 
                        }else {
                            graph  = new PrimGraph(vCount, eCount); 
                        }
                        nodesPanel.setGraph(graph); 
                        nodesPanel.repaint();
                    }
                }catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number of edges or vertexs");
                }
                
            }
        });
        
        //manually set input value;
        kruskalRButton.setSelected(true);
        algorithm = 1; 
        edgeField.setText("20");
        vField.setText("10");
        graph = new Graph(10, 20); 
        nodesPanel.setGraph(graph); 
        nodesPanel.repaint();
        
        Dimension d = new Dimension (1000, 700); 
        setMaximumSize(d);
        setMinimumSize(d); 
        setPreferredSize(d);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    private void initialComponents() {
        
        ButtonGroup group = new ButtonGroup(); 
        group.add(kruskalRButton);
        group.add(primeRButton);
        
        headerPanel.setLayout(new FlowLayout());
        headerPanel.add(kruskalRButton); 
        headerPanel.add(primeRButton); 
        headerPanel.add(vLabel); 
        headerPanel.add(vField); 
        headerPanel.add(edgeLabel); 
        headerPanel.add(edgeField); 
        headerPanel.add(btnApply); 
        
        
        BorderLayout layout  = new BorderLayout(); 
        setLayout(layout); 
        
        Container pane = getContentPane(); 
        pane.add(headerPanel, BorderLayout.NORTH); 
        
        nodesPanel = new NodesPanel(); 
        pane.add(nodesPanel, BorderLayout.CENTER); 
    }
    
}
