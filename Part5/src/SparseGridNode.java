
public class SparseGridNode {
	private Object occupant;
	private int col;
	private SparseGridNode next;

	public SparseGridNode(Object obj, int column, SparseGridNode next_) {
		occupant = obj;
		col = column;
		next = next_;
	}
	public SparseGridNode() {
		next = null;
	}

	public Object getOccupant() {
		return occupant;
	}
	public int getCol() {
		return col;
	}
	public SparseGridNode getNext() {
		return next;
	}
	public void setOccupant(Object obj) {
		occupant = obj;
	}
	public void setCol(int column) {
		col = column;
	}
	public void setNext(SparseGridNode next_) {
		next = next_;
	}
}