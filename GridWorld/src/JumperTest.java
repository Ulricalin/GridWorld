import static org.junit.Assert.*;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import org.junit.Before;
import org.junit.Test;

public class JumperTest {
	private ActorWorld world = new ActorWorld();
	private Jumper jumper = new Jumper();
	@Before
	public void setUp() throws Exception {
		world.add(new Location(0,0), jumper);
	}

	@Test
	public void testTurn() {
		jumper.act();
		assertEquals(45, jumper.getDirection());
	}
	@Test
	public void testJump() {
		jumper.act();
		//can't jump out of grid
		assertEquals(new Location(0,0), jumper.getLocation());
		jumper.act();
		jumper.act();
		//now direction is right
		assertEquals(new Location(0,2), jumper.getLocation());
		//there is a rock in front of jumper
		world.add(new Location(0,3), new Rock());
		jumper.act();
		//jump
		assertEquals(new Location(0,4), jumper.getLocation());
	}
	public void testCanJump() {
		//can't jump out of grid
		assertEquals(false, jumper.canJump());
		jumper.act();
		jumper.act();
		//now direction is right
		world.add(new Location(0,1), new Rock());
		world.add(new Location(0,2), new Rock());
		//two sells in front of it contains a rock,can't jump
		assertEquals(false, jumper.canJump());
		//two sells in front of it contains a Flower,can jump
		world.add(new Location(0,2), new Flower());
		jumper.act();
		world.add(new Location(1,3), new Jumper());
		//can't jump over the other jumper
		assertEquals(false, jumper.canJump());
		jumper.moveTo(new Location(5, 5));
		//can jump over the flower
		world.add(new Location(6,6), new Flower());
		assertEquals(true, jumper.canJump());
		//can jump over the rock
		world.add(new Location(6,6), new Rock());
		assertEquals(true, jumper.canJump());
		jumper.act();
		//can jump if nothing in front of
		assertEquals(true, jumper.canJump());
	}
}
