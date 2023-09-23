package com.zaigard100.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends ApplicationAdapter {
	private ShapeRenderer shape;
	private FitViewport viewport;
	private OrthographicCamera camera;
	SpriteBatch batch;

	Utils utils;
	public int WIDTH,HEIGHT;
	int ZOOM = 40;
	int WALL = 70000;
	@Override
	public void create () {
		batch = new SpriteBatch();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		shape = new ShapeRenderer();
		camera = new OrthographicCamera();
		viewport = new FitViewport(WIDTH, HEIGHT, camera);

		utils = new Utils();
		utils.load_zones("/home/zaigard/Projects/SpSityZone/assets/Города.xlsx",true);

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();

			for(Zone zone:utils.getZones()) {

				zone.render(shape, camera, ZOOM);

				if(zone.getOwner().equals("Zaigard100")) {
					zone.setFilled(true);
					zone.render(shape, camera, ZOOM);
				}
			}

			shape.begin(ShapeRenderer.ShapeType.Line);
			shape.setColor(Color.GREEN);
			shape.line(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
			shape.setColor(Color.BLUE);
			shape.line(0,Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
			shape.setColor(Color.RED);
			shape.line(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight(),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
			shape.setColor(Color.YELLOW);
			shape.line(Gdx.graphics.getWidth()/2,0,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
			shape.setColor(Color.GREEN);
			shape.rect(Gdx.graphics.getWidth()/2-(WALL/2)/ZOOM,Gdx.graphics.getHeight()/2-(WALL/2)/ZOOM,WALL/ZOOM,WALL/ZOOM);
			shape.end();

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
