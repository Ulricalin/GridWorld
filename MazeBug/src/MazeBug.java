

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Stack<Location> myWayInfo = new Stack<Location>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown
	int probility[] = {0,0,0,0};
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		} else {
			goBack();
			stepCount++;
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		int dir[] = {Location.NORTH, Location.RIGHT, Location.HALF_CIRCLE, Location.LEFT};
		for (int direction : dir) {
			Location temp = loc.getAdjacentLocation(direction);
			if (gr.isValid(temp)) {
				Actor actor = gr.get(temp);
				//if the red rock is around, go ahead;
				if ((actor instanceof Rock) && actor.getColor().equals(Color.RED)) {
					ArrayList<Location> goal = new ArrayList<Location>();
					goal.add(temp);
					return goal;
				} else if (actor == null) {
					valid.add(temp);
				}
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		if (getValid(getLocation()).isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		
		ArrayList<Location> thePossibleNext = getValid(loc);
		if (thePossibleNext.isEmpty()) 
			return;
		
//		int max = -1;
//		int index = 0;
//		for (int i = 0; i < thePossibleNext.size(); i++) {
//			Location temp = thePossibleNext.get(i);
//			int d = loc.getDirectionToward(temp)/(Location.RIGHT);
//			if (probility[d] > max) {
//				next = temp;
//				index = d;
//				max = probility[d];
//			}
//		}
//		probility[index]++;
		int r = (int) (Math.random() * thePossibleNext.size());
		
		next = thePossibleNext.get(r);
		last = loc;
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			myWayInfo.add(last);
			Actor actor = gr.get(next);
			//if the red rock is around, go ahead;
			if ((actor instanceof Rock) && actor.getColor().equals(Color.RED)) {
				isEnd = true;
			}
			moveTo(next);
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
	private void goBack() {
		Grid<Actor> gr = getGrid();
		if (gr == null) 
			return;
		Location loc = getLocation();
		if (!myWayInfo.empty()) {
			myWayInfo.pop();
			int lastDir = getLocation().getDirectionToward(last);
//			int d = ((lastDir+Location.HALF_CIRCLE)/Location.RIGHT)%4;
//			probility[d]--;
			setDirection(lastDir);
			moveTo(last);
			if (!myWayInfo.empty()) {
				last = myWayInfo.peek();
			}
			Flower flower = new Flower(getColor());
			flower.putSelfInGrid(gr, loc);
		}
	}
}
