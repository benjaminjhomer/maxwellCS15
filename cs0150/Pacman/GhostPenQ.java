package Pacman;

import java.util.LinkedList;

/*
 * The GhostPenQ class is a wrapper class for a linked list of type Ghost. 
 * It only allows an element of said list to removed if it is 
 * in the first index and every element added is added to the 
 * end. It has 4 methods: enqueue(), dequeue(), size(), and isEmpty().
 * It is contained in the GameLogic class.
 */

public class GhostPenQ {

	private LinkedList<Ghost> _list;

	public GhostPenQ() {
			_list = new LinkedList<Ghost>();
		}

	/*
	 * This method takes in a Ghost as a parameter and returns nothing. It adds the
	 * Ghost to the end of _list.
	 */
	
	public void enqueue(Ghost ghost) {
		_list.addLast(ghost);
	}
	
	/*
	 * This method has no parameters and returns a Ghost. The Ghost it returns will be
	 * the first element in _list.
	 */

	public Ghost dequeue() {
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
