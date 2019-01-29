package util;

import java.text.StringCharacterIterator;
import java.util.ArrayList;

import java.util.List;

import javax.swing.text.StringContent;

public class Graph {
	


	static final int MaxValue=65535;
	static final int M=65535;
	public Node[] Vertex = new Node[9];         
	
 
	public int VertxNum = 9;              
       
	
	int[][] EdgeWeight = {
			{0,4,6,2,M,M,M,M,M},
			{4,0,M,M,2,M,M,M,M},
			{6,M,0,M,M,M,M,3,M},
			{2,M,M,0,5,M,M,M,M},
			{M,2,M,5,0,3,3,M,M},
			{M,M,M,M,3,0,M,M,M},
			{M,M,M,M,3,M,0,M,M},
			{M,M,3,M,M,M,M,0,4},
			{M,M,M,M,M,M,M,4,0}
	};     
	public int[] isTrav = new int[9];            
	
	public static void oneSecondPassed(Graph g,int n){
		int i;
		g.isTrav[n] = 1;
		Node node = g.Vertex[n];
		

		node.pass(g);
		

		node.init(g);
		

		node.arrive(g);
		
		
		for(i = 0; i< g.VertxNum; i++)
		{
		
                        if(g.EdgeWeight[n][i] != g.M && g.isTrav[i] == 0)
				{
                        oneSecondPassed(g, i);     
				}
		}
	}

	public static boolean hasNoPeople(Graph g) {
		for(Node node:g.Vertex) {
			if(!node.ini.isEmpty()||!node.peopleInEdge.isEmpty()||!node.que.isEmpty()) {
				return false;
			}
		}
		
		return true;
	}

	public static int[] GetGuidePath(Graph g, int start, int exit,int speed) {


		int [] result = new int[100];

				
		        int [][] pathMatirx = new int[g.VertxNum][g.VertxNum];

		        int [][] preTable = new int[g.VertxNum][g.VertxNum];
		        
		        for (int i = 0; i < g.VertxNum; i++) {
		            for (int j = 0; j < g.VertxNum; j++) {
		                pathMatirx[i][j] = g.EdgeWeight[i][j]/speed+g.Vertex[j].que.size()/g.Vertex[j].thoughPut;
		                preTable[i][j] = j;
		            }
		        }

		        for (int k = 0; k < g.VertxNum; k++) {

		            for (int m = 0; m < g.VertxNum; m++) {
		                
		                for (int n = 0; n < g.VertxNum; n++) {
		                    
		                    int mn = pathMatirx[m][n];
		                    int mk = pathMatirx[m][k];
		                    int kn = pathMatirx[k][n];
		                    int addedPath = (mk == M || kn == M)? M : mk + kn;
		                    
		                    if (mn > addedPath) {

		                        pathMatirx[m][n] = addedPath;

		                        preTable[m][n] = preTable[m][k];
		                    }
		                    
		                }
		            }
		        }
		        
		        int k = preTable[start][exit];
		        result[0] = start;
		        int id = 1;
		        while(k!=exit) {
		        	result[id] = k;
		        	id++;
		        	k = preTable[k][exit];
		        }
		        result[id] = exit;
		        return result;
	}
	
	
	
	public static int[] GetShortestPath(Graph g, int start, int exit) {

		int [] result = new int[100];

	
        int [][] pathMatirx = new int[g.VertxNum][g.VertxNum];

        int [][] preTable = new int[g.VertxNum][g.VertxNum];
        

        for (int i = 0; i < g.VertxNum; i++) {
            for (int j = 0; j < g.VertxNum; j++) {
                pathMatirx[i][j] = g.EdgeWeight[i][j];
                preTable[i][j] = j;
            }
        }
        

        for (int k = 0; k < g.VertxNum; k++) {

            for (int m = 0; m < g.VertxNum; m++) {
                
                for (int n = 0; n < g.VertxNum; n++) {
                    
                    int mn = pathMatirx[m][n];
                    int mk = pathMatirx[m][k];
                    int kn = pathMatirx[k][n];
                    int addedPath = (mk == M || kn == M)? M : mk + kn;
                    
                    if (mn > addedPath) {

                        pathMatirx[m][n] = addedPath;

                        preTable[m][n] = preTable[m][k];
                    }
                    
                }
            }
        }
        
        int k = preTable[start][exit];
        result[0] = start;
        int id = 1;
        while(k!=exit) {
        	result[id] = k;
        	id++;
        	k = preTable[k][exit];
        }
        result[id] = exit;
        return result;

	}
	


}
