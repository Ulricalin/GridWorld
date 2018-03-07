
import info.gridworld.actor.Actor;
import java.awt.Color;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
/*
 * A ChameleonKid changes its color to the color of one of the actors
 * immediately in front or behind , if there is no actor in these locations
 * then it darkens
*/
public class ChameleonKid extends ChameleonCritter
{
    private static final double DARKENING_FACTOR=0.15;
    
    public ArrayList<Actor> getActors()
    {
    	ArrayList<Actor> actors = new ArrayList<Actor>();
    	int[] dirs =
            { Location.AHEAD, Location.HALF_CIRCLE};
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null) {
            	actors.add(a);
            }
                
        }
        return actors;
    }

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
        return locs;
    }
}
