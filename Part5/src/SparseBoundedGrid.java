import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid<E> extends AbstractGrid<E>{
	private int numRows;
	private int numCols;
	private SparseGridNode [] occupantArray;

	public SparseBoundedGrid(int row, int col) {
		if (row <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (col <= 0)
            throw new IllegalArgumentException("cols <= 0");
		numRows = row;
		numCols = col;
		occupantArray = new SparseGridNode[row];
		for (int i = 0; i < row; i++) {
			occupantArray[i] = new SparseGridNode();
		}
	}
	public E remove(Location loc) {
		if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
		E obj = get(loc);
		if (obj == null) return obj;
		SparseGridNode node = occupantArray[loc.getRow()];
		while (node.getNext().getCol() != loc.getCol()) {
			node = node.getNext();
		}
		SparseGridNode nodeToDelete = node.getNext();
		node.setNext(nodeToDelete.getNext());
		return obj;
	}
		
	public E put(Location loc, E obj) {
		if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
		E oldObj = remove(loc);
		SparseGridNode node = occupantArray[loc.getRow()];
		SparseGridNode newNode = new SparseGridNode(obj, loc.getCol(), node.getNext());
		node.setNext(newNode);
		return oldObj;
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
		for (int i = 0; i < numRows; i++) {
			SparseGridNode node = occupantArray[i].getNext();
			while (node != null) {
				locs.add(new Location(i, node.getCol()));
				node = node.getNext();
			}
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
		SparseGridNode node = occupantArray[loc.getRow()].getNext();
		while (node != null && node.getCol() != loc.getCol()) {
			node = node.getNext();
		}
		if (node != null) {
			return (E)node.getOccupant();
		} else {
			return null;
		}
	}
}
