import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import java.awt.Color;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/* A RockHound can just eat Rock*/
public class RockHound extends Critter
{
	public void processActors(ArrayList<Actor> actors) {
		for (Actor a: actors) {
			if (a instanceof Rock) {
				a.removeSelfFromGrid();
			}
		}
	}
}
