package util;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Node {
	public Queue<People> que = new LinkedList<>(); 
	public List<People> ini = new ArrayList<>();
	public List<People> peopleInEdge = new ArrayList<>();
	public int nodeId = 0;
	
	public Node(int nodeId, int thoughPut) {
		super();
		this.nodeId = nodeId;
		this.thoughPut = thoughPut;
	}

	public int thoughPut = 2;
	
	public void pass(Graph g) {
		
			for(int j=0;j<this.thoughPut;j++) {
				if(!this.que.isEmpty()) {
					People people = this.que.poll();
					people.fromNode = this.nodeId;
					people.towardsNode = people.escapePath[people.position+1];
					people.walkTime = g.EdgeWeight[people.fromNode][people.towardsNode];
					this.peopleInEdge.add(people);
				}
				
			}
	}

	public void arrive(Graph g) {
		for (Iterator<People> it = this.peopleInEdge.iterator(); it.hasNext();) {
			People item = it.next();
			if(item.walkTime!=0 ) {
				item.walkTime = item.walkTime - item.speed;
			}
			if(item.walkTime <= 0) {
				item.position++;
				g.Vertex[item.towardsNode].que.add(item);
				it.remove();
			}
		}
		
	}

	public void init(Graph g) {
		for (Iterator<People> it = this.ini.iterator(); it.hasNext();) {
			People item =  it.next();
			if(item.waitTime!=0) {
				item.waitTime--;
			}
			if(item.waitTime ==0) {
				item.escapePath = Graph.GetShortestPath(g,this.nodeId,item.exitNode);
				this.que.add(item);
				it.remove();
			}
		}
		
	}
	
}
