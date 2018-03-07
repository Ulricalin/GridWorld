import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/* A QuickCrab walk two spaces in one time*/
public class QuickCrab extends CrabCritter{
	public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Location loc = getLocation();
        Grid<Actor> gr = getGrid();
        Location left1 = loc.getAdjacentLocation(getDirection() + Location.LEFT);
        if (gr.isValid(left1) && gr.get(left1) == null) {
        	Location left2 = left1.getAdjacentLocation(getDirection() + Location.LEFT);
        	if (gr.isValid(left2) && gr.get(left2) == null) {
        		locs.add(left2);
        	}
        }
        Location right1 = loc.getAdjacentLocation(getDirection() + Location.RIGHT);
        if (gr.isValid(right1) && gr.get(right1) == null) {
        	Location right2 = right1.getAdjacentLocation(getDirection() + Location.RIGHT);
        	if (gr.isValid(right2) && gr.get(right2) == null) {
        		locs.add(right2);
        	}
        }
        return locs;
    }
}
