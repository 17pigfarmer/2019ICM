package util;


public class People {
	static final int MaxNum=100;  
	int fromNode;
	public People(int fromNode, int towardsNode, int waitTime, int walkTime,  int position,
			int exitNode,int speed) {
		super();
		this.fromNode = fromNode;
		this.towardsNode = towardsNode;
		this.waitTime = waitTime;
		this.walkTime = walkTime;
		this.position = position;
		this.exitNode = exitNode;
		this.speed = speed;
	}
	int towardsNode;
	int waitTime;
	int walkTime;
	int[] escapePath = new int[MaxNum];
	int position;
	int exitNode;
	int speed;
}
