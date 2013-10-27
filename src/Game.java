
import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

public class Game implements ApplicationListener, InputProcessor {
    
	private static final int MAX_CATS = 100;
	private SpriteBatch spriteBatch;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private long nextTimeForCatToAppear = 0;
	private long maxWait = 450;
	private Random random = new Random();
	private long minWait = 120;
	private int score = 0;
	private BitmapFont font;
	public Cat[] cats = new Cat[MAX_CATS];
	private boolean touchFlag = false;
	
	private Cat getUnappearedCat() {
		for (int i = 0; i < MAX_CATS; i++) {
			if (cats[i] == null) {
				cats[i] = new Cat();
				return cats[i];
			} else if (!cats[i].isAppeared())
				return cats[i];
		}
		return null;
	}
	
	public void create () {
    	Assets.load();
    	spriteBatch = new SpriteBatch();
    	camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(Gdx.graphics.getWidth() >> 1, Gdx.graphics.getHeight() >> 1,0);
  
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
    		
		spriteBatch.enableBlending();
		Gdx.input.setInputProcessor(this);
		Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());

		spriteBatch.setProjectionMatrix(normalProjection);
		font  = new BitmapFont();
	}


    public void render () {
        	
    	if (System.currentTimeMillis() > nextTimeForCatToAppear) {
    	Cat cat = getUnappearedCat();
    	nextTimeForCatToAppear = System.currentTimeMillis() + random.nextInt((int)(maxWait - minWait)) + minWait;
    	if (cat != null)
    		cat.appear();
    	}
    	
    	
    	for (int  i =MAX_CATS - 1; i >= 0 ; i--)
    		if (cats[i] != null) {
    			cats[i].update();
    			if (cats[i].isAppeared())
    				if (Gdx.input.isTouched() && !touchFlag)
	        			if (cats[i].isClicked(Gdx.input.getX(),Gdx.graphics.getHeight() - Gdx.input.getY())) {
	        				cats[i].dissappear();
	        				if (cats[i].getType() == Cat.Type.REGULAR)
	        					score += 25;
	        				else if (cats[i].getType() == Cat.Type.EVIL)
	        					score -= 100;
	        				touchFlag = true;
	        				break;
	        			}
    		}
    	
    	if (Gdx.input.isTouched() == false)
    		touchFlag = false;
    			
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		spriteBatch.begin();
    	renderAllCats();
    	font.draw(spriteBatch, "Swag: " + score, 10,20);
		font.draw(spriteBatch, "Swagcats", 410,20);
		font.draw(spriteBatch, "Alex Spedding", 195,20);
		spriteBatch.end();
	}
	
	private void renderAllCats() {
		for (int  i =0; i < MAX_CATS; i++)
			if (cats[i] != null)
				cats[i].render(spriteBatch);
	}
	
	public void resize (int width, int height) {
	}
	
	public void pause () {
	}
	
	public void resume () {
	}
	
	public void dispose () {
	}
	
	
	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}
}