import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;


public class SparseUnBoundGrid<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray; // the array storing the grid elements
    private int length;
    
    public SparseUnBoundGrid()
    {
        length = 16;
        occupantArray = new Object[length][length];
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow()  && 0 <= loc.getCol();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < length; r++)
        {
            for (int c = 0; c < length; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (loc.getRow() >= length || loc.getCol() >= length) {
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        if (loc.getRow() >= length || loc.getCol() >= length) {
            resize(loc);
        }
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        if (loc.getRow() >= length || loc.getCol() >= length) {
            return null;
        }
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
    private void resize(Location loc) {
        int newLength = length;
        while (loc.getRow() >= newLength || loc.getCol() >= newLength) {
            newLength *= 2;
        }
        Object[][] newOccupantArray = new Object[newLength][newLength];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                newOccupantArray[i][j] = occupantArray[i][j];
            }
        }
        occupantArray = newOccupantArray;
        length = newLength;
    }
}