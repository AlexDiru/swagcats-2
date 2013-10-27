import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Assets {

	public static Texture regularCatTexture, evilCatTexture;
	
	public static void load() {
		Gdx.files = new com.badlogic.gdx.backends.lwjgl.LwjglFiles();
		regularCatTexture = new Texture(Gdx.files.internal("assets/cat.png"));
		evilCatTexture = new Texture(Gdx.files.internal("assets/evilcat.png"));
	}
	
}
