package model.collectibles;

import javafx.scene.image.ImageView;
import model.characters.Hero;
import model.world.CharacterCell;
import engine.Game;
import model.characters.Character;

public class Supply implements Collectible {

	
	private ImageView iv;
	
	
	public ImageView getiv() {
		return iv;
	}
	
	public void setiv(ImageView v) {
		iv=v;
	}
	
	
	
	@Override
	public void pickUp(Hero h) {
		h.getSupplyInventory().add(this);
	}

	@Override
	public void use(Hero h) {
		h.getSupplyInventory().remove(this);
	}

}
