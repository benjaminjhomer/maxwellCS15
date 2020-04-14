package Pacman;

import java.util.ArrayList;
import cs015.fnl.PacmanSupport.BoardLocation;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile {

	private Rectangle _tile;
	private BoardLocation _type;
	private ArrayList<Interactable> _contain;
	private Direction _direction;

	public Tile(BoardLocation type) {
		_tile = new Rectangle(Constants.TILE_SIDE_L, Constants.TILE_SIDE_L);
		_type = type;
		_contain = new ArrayList<Interactable>();
		if (_type == BoardLocation.WALL) {
			_tile.setFill(Color.BLUE);
		} else {
			_tile.setFill(Color.BLACK);
		}
	}

	public Rectangle getNode() {
		return _tile;
	}

	public void addArray(Interactable inter) {
		_contain.add(inter);
	}

	public void removeArray(Interactable inter) {
		_contain.remove(inter);
	}

	public BoardLocation getType() {
		return _type;
	}

	public void setDirection(Direction d) {
		_direction = d;
	}

	public Direction getDirection() {
		return _direction;
	}

	public boolean[] checkList(boolean b) {
		boolean[] result = new boolean[3];
		result[0] = false;
		result[1] = false;
		result[2] = false;
		int listSize = _contain.size();
		for (int i = 0; i < listSize; i++) {
			switch (_contain.get(i).isType()) {
			case DOT:
				result[0] = true;
				if (b) {
					_contain.get(i).interacted();
					_contain.remove(i);
					listSize--;
					i--;
				}
				break;
			case ENERGIZER:
				result[1] = true;
				if (b) {
					_contain.get(i).interacted();
					_contain.remove(i);
					i--;
					listSize--;
				}
				break;
			case GHOST:
				result[2] = true;
				break;
			}
		}
		return result;
	}
}
