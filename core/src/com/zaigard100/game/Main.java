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
	SpriteBatch batch;

	Utils utils;
	public int WIDTH,HEIGHT;
	int ZOOM = 80;
	int WALL = 40000; //барьер
	int old_x = 0;// 70-108 строки
	int old_y = 0;

	//смещения
	int dx = 0; //клавиши A D
	int dy = 0; //клавишы W S
	int speed = 2; //скорость управления регулеровка клавиши Q E
	@Override
	public void create () {
		batch = new SpriteBatch();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		shape = new ShapeRenderer();

		utils = new Utils();
		utils.load_zones("/home/zaigard/Projects/SpSityZone/assets/Города.xlsx",true); //Ексель файл по надобность добавить новые города

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();

			for(Zone zone:utils.getZones()) {

				zone.render(shape, ZOOM,dx,dy);

				if(zone.getOwner().equals("Zaigard100")) {
					zone.setFilled(true);
					zone.render(shape, ZOOM,dx,dy);
				}
			}

			shape.begin(ShapeRenderer.ShapeType.Line);
			shape.setColor(Color.GREEN);
			shape.line(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/2+dy,Gdx.graphics.getWidth()/2+dx,Gdx.graphics.getHeight()/2+dy);
			shape.setColor(Color.BLUE);
			shape.line(0,Gdx.graphics.getHeight()/2+dy,Gdx.graphics.getWidth()/2+dx,Gdx.graphics.getHeight()/2+dy);
			shape.setColor(Color.RED);
			shape.line(Gdx.graphics.getWidth()/2+dx,Gdx.graphics.getHeight(),Gdx.graphics.getWidth()/2+dx,Gdx.graphics.getHeight()/2+dy);
			shape.setColor(Color.YELLOW);
			shape.line(Gdx.graphics.getWidth()/2+dx,0,Gdx.graphics.getWidth()/2+dx,Gdx.graphics.getHeight()/2+dy);
			shape.setColor(Color.GREEN);
			shape.rect(Gdx.graphics.getWidth()/2-WALL/ZOOM+dx,Gdx.graphics.getHeight()/2-WALL/ZOOM+dy,WALL*2/ZOOM,WALL*2/ZOOM);
			shape.end();

		batch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
			old_x = dx*ZOOM;
			old_y = dy*ZOOM;
			ZOOM--;
			if(ZOOM <= 0){
				ZOOM = 1;
			}
			dx= old_x/ZOOM;//нармализация координат после зума
			dy= old_y/ZOOM;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
			old_x = dx*ZOOM;
			old_y = dy*ZOOM;
			ZOOM++;
			if(ZOOM <= 0){
				ZOOM = 1;
			}
			dx= old_x/ZOOM;
			dy= old_y/ZOOM;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_ADD)){
			old_x = dx*ZOOM;
			old_y = dy*ZOOM;
			ZOOM-=10;
			if(ZOOM <= 0){
				ZOOM = 1;
			}
			dx= old_x/ZOOM;
			dy= old_y/ZOOM;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_SUBTRACT)){
			old_x = dx*ZOOM;
			old_y = dy*ZOOM;
			ZOOM+=10;
			if(ZOOM <= 0){
				ZOOM = 1;
			}
			dx= old_x/ZOOM;
			dy= old_y/ZOOM;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			dy-=speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)){
			dy+=speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			dx+=speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			dx-=speed;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
			speed--;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
			speed++;
		}
	}

	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
