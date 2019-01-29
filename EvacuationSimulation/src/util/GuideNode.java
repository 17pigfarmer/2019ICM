package util;

import java.util.ArrayList;
import java.util.LinkedList;

public class GuideNode extends Node {
		int exitnode;
	public GuideNode(int nodeId, int thoughPut) {
		super(nodeId, thoughPut);
		this. que = new LinkedList<>(); 
		this. ini = new ArrayList<>();
		this. peopleInEdge = new ArrayList<>();
		
	}

	@Override
	public void pass(Graph g) {
		for(int j=0;j<this.thoughPut;j++) {
			if(!this.que.isEmpty()) {
				People people = this.que.poll();
				people.escapePath = Graph.GetGuidePath(g,this.nodeId,this.exitnode,people.speed);
				people.position = 0;
				people.fromNode = this.nodeId;
				people.towardsNode = people.escapePath[people.position+1];
				people.walkTime = g.EdgeWeight[people.fromNode][people.towardsNode];
				this.peopleInEdge.add(people);
			}
			
		}
	}


}
