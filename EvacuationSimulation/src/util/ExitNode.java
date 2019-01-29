package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ExitNode extends Node {

	public ExitNode(int nodeId, int thoughPut) {
		super(nodeId, thoughPut);
		this. que = new LinkedList<>(); 
		this. ini = new ArrayList<>();
		this. peopleInEdge = new ArrayList<>();
	}

	@Override
	public void pass(Graph g) {
		
			for(int j=0;j<this.thoughPut;j++) {
				while(!this.que.isEmpty()) {
				this.que.poll();
				}
			}
		
	}

	@Override
	public void init(Graph g) {
		
	}

	@Override
	public void arrive(Graph g) {
		
	}
	
}
