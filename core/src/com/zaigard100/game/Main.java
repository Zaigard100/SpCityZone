package com.zaigard100.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	int ZOOM = 80;
	int WALL = 70000;
	int dx = 0;
	int dy = 0;
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

				zone.render(shape, camera, ZOOM,dx,dy);

				if(zone.getOwner().equals("Zaigard100")) {
					zone.setFilled(true);
					zone.render(shape, camera, ZOOM,dx,dy);
				}
			}

			shape.begin(ShapeRenderer.ShapeType.Line);
			shape.setColor(Color.GREEN);
			shape.line(Gdx.graphics.getWidth()+dx,Gdx.graphics.getHeight()/2+dy,Gdx.graphics.getWidth()/2+dx,Gdx.graphics.getHeight()/2+dy);
			shape.setColor(Color.BLUE);
			shape.line(dx,Gdx.graphics.getHeight()/2+dy,Gdx.graphics.getWidth()/2+dx,Gdx.graphics.getHeight()/2+dy);
			shape.setColor(Color.RED);
			shape.line(Gdx.graphics.getWidth()/2+dx,Gdx.graphics.getHeight()+dy,Gdx.graphics.getWidth()/2+dx,Gdx.graphics.getHeight()/2+dy);
			shape.setColor(Color.YELLOW);
			shape.line(Gdx.graphics.getWidth()/2+dx,dy,Gdx.graphics.getWidth()/2+dx,Gdx.graphics.getHeight()/2+dy);
			shape.setColor(Color.GREEN);
			shape.rect(Gdx.graphics.getWidth()/2-(WALL/2)/ZOOM+dx,Gdx.graphics.getHeight()/2-(WALL/2)/ZOOM+dy,WALL/ZOOM,WALL/ZOOM);
			shape.end();

		batch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
			ZOOM++;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
			ZOOM--;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_ADD)){
			ZOOM+=10;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_SUBTRACT)){
			ZOOM-=10;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			dy++;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)){
			dy--;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			dx--;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			dx++;
		}
	}

	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
