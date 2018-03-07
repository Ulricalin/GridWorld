import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/*
 * A BlusterCritter looks at all of the neighbors within two steps of its current
 * loction.It counts the number of critters in those lacations.
 * if it's courage <= number it become dark
 * if it's courage > num it become bright
 */
public class BlusterCritter extends Critter
{
	private int courage;
	private static final double DARKENING_FACTOR = 0.15;
	private static final double BRIGHTRNING_FACTOR = 0.15;
	BlusterCritter() {
		super();
		courage = 5;
	}

	BlusterCritter(int c) {
		super();
		courage = c;
	}

	public ArrayList<Actor> getActors() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Grid<Actor> gr = getGrid();
        Location myloc = getLocation();
        for (int i = myloc.getRow()-2; i <= myloc.getRow()+2; i++)
        {
        	for (int j = myloc.getCol()-2; j <= myloc.getCol()+2; j++) {
        		Location loc = new Location(i, j);
        		if (gr.isValid(loc) && loc.getRow() != myloc.getRow() && loc.getCol() != myloc.getCol()) {
        			Actor a = getGrid().get(loc);
        			if (a != null && a instanceof Critter) {
        				actors.add(a);
        			}
        		}
        	}
        }
        return actors;
	}
	public boolean cmpCourage(int c) {
		if (c < courage) {
			return true;
		} else {
			return false;
		}
	}
	public void processActors(ArrayList<Actor> actors) {
		if (cmpCourage(actors.size())) {
			BeBright(getColor());
		} else {
			BeDark(getColor());
		}
	}
	public void BeDark(Color c) {
        int red = (int)(c.getRed()*(1 - DARKENING_FACTOR));
        int green = (int)(c.getGreen()*(1 - DARKENING_FACTOR));
        int blue = (int)(c.getBlue()*(1 - DARKENING_FACTOR));
        if (red <= 10 && c.getRed() != 0) red = 10;
        if (blue <= 10 && c.getBlue() != 0) blue = 10;
        if (green <= 10 && c.getGreen() != 0) green = 10;
        setColor(new Color(red,green,blue));
    }
    public void BeBright(Color c) {
        int red = (int)(c.getRed()*(1 + BRIGHTRNING_FACTOR));
        int green = (int)(c.getGreen()*(1 + BRIGHTRNING_FACTOR));
        int blue = (int)(c.getBlue()*(1 + BRIGHTRNING_FACTOR));
        if (red > 255) red = 255;
        if (blue > 255) blue = 255;
        if (green > 255) green = 255;
        setColor(new Color(red,green,blue));
    }
}
