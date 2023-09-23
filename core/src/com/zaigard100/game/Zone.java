package com.zaigard100.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Zone {

    String name;
    String description;
    boolean isCity;
    String owner;
    int over_x,over_y;
    int radius;
    int nether_x,nether_y;
    Color color;

    public Zone(String name, String description, boolean isCity, String owner, int over_x, int over_y, int radius, int nether_x, int nether_y,Color color) {
        this.name = name;
        this.description = description;
        this.isCity = isCity;
        this.owner = owner;
        this.over_x = over_x;
        this.over_y = over_y;
        this.radius = radius;
        this.nether_x = nether_x;
        this.nether_y = nether_y;
        this.color =color;
    }

    public void render(ShapeRenderer shape, Camera camera,float SCALE) {
        shape.setProjectionMatrix(camera.combined);
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setColor(color);
        shape.rect((over_x-radius)*SCALE,(over_y-radius)*SCALE,radius*2*SCALE,radius*2*SCALE);
        shape.end();
    }

}
