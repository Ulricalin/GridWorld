import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid2<E> extends AbstractGrid<E>{
	private int numRows;
	private int numCols;
	private Map<Location, E> occupantMap;

	public SparseBoundedGrid2(int row, int col) {
		if (row <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (col <= 0)
            throw new IllegalArgumentException("cols <= 0");
		numRows = row;
		numCols = col;
		occupantMap = new HashMap<Location, E>();
	}
	public E remove(Location loc) {
		if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
		
		return occupantMap.remove(loc);
	}
		
	public E put(Location loc, E obj) {
		if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
		return occupantMap.put(loc, obj);
	}
	
	public boolean isValid(Location loc) {
		if (0 <= loc.getRow() && loc.getRow() < getNumRows() && 0 <= loc.getCol()
				&& loc.getCol() < getNumCols()) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<Location> getOccupiedLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();
		for (Location loc : occupantMap.keySet()) {
			locs.add(loc);
		}
		return locs;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public E get(Location loc) {
		if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
		return occupantMap.get(loc);
	}
}
