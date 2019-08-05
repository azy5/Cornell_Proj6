package student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import a5.GraphAlgorithms;
import game.FindState;
import game.FleeState;
import game.NodeStatus;
import game.SewerDiver;
import game.Node;

import common.NotImplementedError;
 
public class DiverMin implements SewerDiver {

	/** Get to the ring in as few steps as possible. Once you get there, <br>
	 * you must return from this function in order to pick<br>
	 * it up. If you continue to move after finding the ring rather <br>
	 * than returning, it will not count.<br>
	 * If you return from this function while not standing on top of the ring, <br>
	 * it will count as a failure.
	 *
	 * There is no limit to how many steps you can take, but you will receive<br>
	 * a score bonus multiplier for finding the ring in fewer steps.
	 *
	 * At every step, you know only your current tile's ID and the ID of all<br>
	 * open neighbor tiles, as well as the distance to the ring at each of <br>
	 * these tiles (ignoring walls and obstacles).
	 *
	 * In order to get information about the current state, use functions<br>
	 * currentLocation(), neighbors(), and distanceToRing() in state.<br>
	 * You know you are standing on the ring when distanceToRing() is 0.
	 *
	 * Use function moveTo(long id) in state to move to a neighboring<br>
	 * tile by its ID. Doing this will change state to reflect your new position.
	 *
	 * A suggested first implementation that will always find the ring, but <br>
	 * likely won't receive a large bonus multiplier, is a depth-first walk. <br>
	 * Some modification is necessary to make the search better, in general. */
	ArrayList<Long> Visit = new ArrayList<Long>();//places visited
	ArrayList<Long> Candidate = new ArrayList<Long>(); //places in wait
	HashMap<Long, Long> Pred = new HashMap<Long, Long>(); // the predecessor of the place
	ArrayList<NodeStatus> NCand = new ArrayList<NodeStatus>(); // places in wait in Node Status
	@Override
	public void find(FindState state) {
		/*if(state.distanceToRing()==0) {
			return;
		}
		for(NodeStatus neighbor : state.neighbors()) {
			if(neighbor.getDistanceToTarget()==0) {
			   state.moveTo(neighbor.getId());
			   return;
			}
		}
		find(state);
		*/
		ArrayList<NodeStatus> vised= new ArrayList<NodeStatus>();
		/*ArrayList<NodeStatus> vised2= new ArrayList<NodeStatus>();
		ArrayList<NodeStatus> vised3= new ArrayList<NodeStatus>();*/
		
		while(state.distanceToRing()!=0) {
			int b=10000;
			NodeStatus node=null;
			for(NodeStatus neighbor : state.neighbors()) {
				int n= neighbor.getDistanceToTarget();
				if(neighbor.getDistanceToTarget()== 0) {
					state.moveTo(neighbor.getId());
					return;
				}
				if(b>n && !(vised.contains(neighbor))) {
					b=n;
					node= neighbor;
					
				}
			}
			if(node==null) {
				Visit.add(state.currentLocation());
				dfs(state);
			}
			/*if(node== null) {
				/*i++;
				state.moveTo(vised.get(vised.size()-i-1).getId());
				continue;
				b=0;
				for(NodeStatus neighbor : state.neighbors()) {
					int n= neighbor.getDistanceToTarget();
					if(b<n && !(vised2.contains(node))) {
						b=n;
						node= neighbor;
					}
				}
			}*/
			/*if(node== null) {
				b=100000;
				for(NodeStatus neighbor : state.neighbors()) {
					int n= neighbor.getDistanceToTarget();
					if(b<n && !(vised3.contains(node))) {
						b=n;
						node= neighbor;
					}
				}
			}*/
			if(state.distanceToRing()!=0 && node!=null) {
			/*if(vised.contains(node)&&vised2.contains(node))	
			vised3.add(node);
			 if(vised.contains(node))
				vised2.add(node);*/
				
					vised.add(node);
				//Visit.add(node.getId());
			state.moveTo(node.getId());
			}
		}
		
    //throw new NotImplementedError();
	}
	/**
	 * Recursion: depth First Search
	 * @param state the findState
	 * @return the place where the ring is
	 */
	public Long dfs(FindState state) {
		
		Long nextMove = state.currentLocation(); 
		
		System.out.println("return dfs: "+state.currentLocation());

		ArrayList<Long> test = new ArrayList<Long>();
		
		for(NodeStatus n: state.neighbors()) {// add all neighboring places to candidate and NCand
			if(n.getDistanceToTarget()== 0) {
				state.moveTo(n.getId());
				return nextMove;
			}		
			if(!Visit.contains(n.getId())) {    // execpt for places already visited
				Candidate.add(n.getId());
				NCand.add(n);
				test.add(n.getId());
			}
		}
		
		System.out.println("after neighboring: "+Candidate.toString());
		System.out.println("New add:"+test.toString());
			
		if(test.isEmpty()) { // if this the place is a dead end or alll its neighbors is visited
			System.out.println("dead end");
			
			if(Candidate.get(Candidate.size()-1)==state.currentLocation()) {
				Candidate.remove(Candidate.size()-1);
				NCand.remove(NCand.size()-1);
			}
			
			System.out.println("my parent:"+Pred.get(state.currentLocation()));
			
			state.moveTo(Pred.get(state.currentLocation()));
			
			Visit.add(state.currentLocation());
			
			if(!Candidate.isEmpty()) {
				dfs(state);
			}
			
		}else { //move to the last candidate
			nextMove = Candidate.get(Candidate.size()-1);
			Pred.put(nextMove, Visit.get(Visit.size()-1));
			state.moveTo(nextMove);
			Visit.add(nextMove);
			Candidate.remove(Candidate.size()-1);
			
			
			System.out.println("ID: "+ nextMove+" distance:"+NCand.get(NCand.size()-1).getDistanceToTarget());
			if(NCand.get(NCand.size()-1).getDistanceToTarget()!=0) {
				NCand.remove(NCand.size()-1);
				dfs(state);
			}	
		}
				
		return nextMove;
	}
	
	/** Flee the sewer system before the steps are all used, trying to <br>
	 * collect as many coins as possible along the way. Your solution must ALWAYS <br>
	 * get out before the steps are all used, and this should be prioritized above<br>
	 * collecting coins.
	 *
	 * You now have access to the entire underlying graph, which can be accessed<br>
	 * through FleeState. currentNode() and getExit() will return Node objects<br>
	 * of interest, and getNodes() will return a collection of all nodes on the graph.
	 *
	 * You have to get out of the sewer system in the number of steps given by<br>
	 * getStepsRemaining(); for each move along an edge, this number is <br>
	 * decremented by the weight of the edge taken.
	 *
	 * Use moveTo(n) to move to a node n that is adjacent to the current node.<br>
	 * When n is moved-to, coins on node n are automatically picked up.
	 *
	 * You must return from this function while standing at the exit. Failing <br>
	 * to do so before steps run out or returning from the wrong node will be<br>
	 * considered a failed run.
	 *
	 * Initially, there are enough steps to get from the starting point to the<br>
	 * exit using the shortest path, although this will not collect many coins.<br>
	 * For this reason, a good starting solution is to use the shortest path to<br>
	 * the exit. */
	@Override
	public void flee(FleeState state) {
		int i=0;
				
				 for(Node n:GraphAlgorithms.shortestPath(state.currentNode(), state.getExit())) {
					 if(i>0) {//omit move from start to start
						 state.moveTo(n); //move according to the shortest path route
					 }
					 i++;
				 }
				
			
	}

}