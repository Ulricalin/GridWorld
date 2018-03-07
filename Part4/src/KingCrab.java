import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
/*
 * A KingCrab gets the actors to be processed the same way a CrabCritter
 * does. It causes each actor that it processes to move one location
 * futher away from the KingCrab.If the actor can not move away, the
 * KingCrab will remove it from Grid.And after completing the process,
 * KingCrab move as a CrabCritter
 */
public class KingCrab extends CrabCritter{
	public void processActors(ArrayList<Actor> actors) {
		Grid<Actor> gr = getGrid();
		for (Actor a : actors) {
			if (!canMoveAway(a)) {
				a.removeSelfFromGrid();
			} else {
				MoveAwayFromKing(a);
			}
		}
	}
	private ArrayList<Location> getMoveAwayLocationsList(Actor a) {
		Grid<Actor> gr = getGrid();
		ArrayList<Location> locs = gr.getEmptyAdjacentLocations(a.getLocation());
		ArrayList<Location> resultList = new ArrayList<Location>();
		for (Location loc : locs) {
			if (isFuther(loc, a.getLocation())) {
				resultList.add(loc);
			}
		}
		return resultList;
	}
	private boolean canMoveAway(Actor a) {
		return getMoveAwayLocationsList(a).size() == 0 ? false : true;
	}
	private void MoveAwayFromKing(Actor a) {
		ArrayList<Location>  locs = getMoveAwayLocationsList(a);
		int r = (int) (Math.random() * locs.size());
		Location loc = locs.get(r);
		a.removeSelfFromGrid();
		a.putSelfInGrid(getGrid(), loc);
	}
	private boolean isFuther(Location newloc, Location oldloc) {
		Location loc = getLocation();
		int newDistance = (newloc.getRow()-loc.getRow())*(newloc.getRow()-loc.getRow())
						 +(newloc.getCol()-loc.getCol())*(newloc.getCol()-loc.getCol());
		int oldDistance = (oldloc.getRow()-loc.getRow())*(oldloc.getRow()-loc.getRow())
						 +(oldloc.getCol()-loc.getCol())*(oldloc.getCol()-loc.getCol());
		return newDistance > oldDistance;		 
	}
}
