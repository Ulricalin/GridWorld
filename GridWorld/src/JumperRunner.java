
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains Jumper. <br />
 * 
 */
public class JumperRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        alice.setColor(Color.ORANGE);
        Jumper bob = new Jumper();
        world.add(new Rock());
        world.add(new Flower());
        world.add(new Bug());
        world.add(new Location(7, 8), alice);
        world.add(new Location(5, 5), bob);
        world.show();
    }
}