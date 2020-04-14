package Pacman;

import javafx.scene.Node;

public interface Interactable {
	
	public Node getNode();
	public Consumable isType();
	public void interacted();
	
}
