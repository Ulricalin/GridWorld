
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;

import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.awt.Color;
/**
 * A <code>Jumper</code>  <br />
 * 
 */
public class Jumper extends Actor
{
    
    public Jumper() {}
    public Jumper(Color c) {
        setColor(c);
    }

    /**
     * Moves to the next location.
     */
    public void act()
    {
        if (canJump()) {
            jump();
        } else {
            turn();
        }
    }
    private void turn() {
        setDirection(getDirection()+Location.HALF_RIGHT);
    }

    private void jump() {
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        next = next.getAdjacentLocation(getDirection());
        moveTo(next);
    }

    public Boolean canJump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        } 
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nnext = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(next) || !gr.isValid(nnext)) {
            return false;
        }
        Actor neibor = gr.get(next);
        Actor nneibor = gr.get(nnext);
        if (!(nneibor == null || nneibor instanceof Flower)) {
            return false;
        }
        if (!(neibor == null || neibor instanceof Flower 
            || neibor instanceof Rock)) {
            return false;
        }
        return true;
    }
}
