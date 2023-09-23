package com.zaigard100.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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

	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		shape = new ShapeRenderer();
		camera = new OrthographicCamera();
		viewport = new FitViewport(WIDTH, HEIGHT, camera);
		batch = new SpriteBatch();
		utils = new Utils();
		utils.load_zones("/home/zaigard/Projects/SpSityZone/assets/Города.xlsx",true);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
			for(Zone zone:utils.getZones()) {
				zone.render(shape,camera,0.5f);
			}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
