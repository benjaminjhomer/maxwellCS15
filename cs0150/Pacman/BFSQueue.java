package Pacman;

import java.util.LinkedList;

/*
 * The BFSQueue class is a wrapper class for a linked list of type Tile. 
 * It only allows an element of said list to removed if it is 
 * in the first index and every element added is added to the 
 * end. It has 4 methods: enqueue(), dequeue(), size(), and isEmpty().
 * It is contained in the GameLogic class.
 */

public class BFSQueue {

	private LinkedList<Tile> _list;

	public BFSQueue() {
		_list = new LinkedList<Tile>();
	}

	/*
	 * This method takes in a Tile as a parameter and returns nothing. It adds the
	 * Tile to the end of _list.
	 */

	public void enqueue(Tile tile) {
		_list.addLast(tile);
	}

	/*
	 * This method has no parameters and returns a Tile. The Tile it returns will be
	 * the first element in _list.
	 */

	public Tile dequeue() {
		return _list.removeFirst();
	}

	/*
	 * This method has no parameters and returns an integer. The integer will be the
	 * size of _list.
	 */

	public int size() {
		return _list.size();
	}

	/*
	 * This method takes in no parameters and returns a boolean. It will return true
	 * if the size of _list is 0 and false if it is greater than 0.
	 */

	public boolean isEmpty() {
		return _list.isEmpty();
	}
}
