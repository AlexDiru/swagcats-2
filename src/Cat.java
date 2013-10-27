import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Cat extends Object2D {

	private static final long TIMEOUT = 12000;
	
	private boolean appeared = false;
	private static Random random = new Random();
	private Type type;
	private long createdTime;
	
	public enum Type {
		REGULAR,
		EVIL
	}
	
	public Cat() {
	}
	
	public void update() {
		if (System.currentTimeMillis() > createdTime + TIMEOUT)
			dissappear();
	}
	
	public void render(SpriteBatch spriteBatch) {
		if (appeared) {
			if (type == Type.REGULAR)
				spriteBatch.draw(Assets.regularCatTexture, x,y);
			else if (type == Type.EVIL)
				spriteBatch.draw(Assets.evilCatTexture,x,y);
		}
	}
	
	public void appear() {
		if (!appeared) {
			type = randomType();
			x = random.nextInt(Gdx.graphics.getWidth() - Assets.regularCatTexture.getWidth());
			y = random.nextInt(Gdx.graphics.getHeight() - Assets.regularCatTexture.getHeight());
			appeared = true;
			createdTime = System.currentTimeMillis();
		}
	}

	public boolean isAppeared() {
		return appeared;
	}
	
	public boolean isClicked(int mx, int my) {
		return (mx >= x && my >= y && mx < x + Assets.regularCatTexture.getWidth() && my < y + Assets.regularCatTexture.getHeight());
	}

	public void dissappear() {
		appeared = false;
	}
	
	public Type getType() {
		return type;
	}
	
	private static Type randomType() {
		int i = random.nextInt(Type.values().length);
		return Type.values()[i];
	}
}
