package com.zaigard100.game;

import com.badlogic.gdx.Gdx;
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
    boolean just = false;
    boolean filled = false;
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
        this.color = new Color((float) Math.random(),(float) Math.random(),(float) Math.random(),1.0f);
        //System.out.println(toString());
        System.out.println(((over_x-radius)/10)+" "+(over_x-radius)/10);
    }

    public void render(ShapeRenderer shape, Camera camera,float SCALE) {
        if(filled){
            shape.begin(ShapeRenderer.ShapeType.Filled);
        }else {
            shape.begin(ShapeRenderer.ShapeType.Line);
        }
        shape.setColor(color);
        shape.rect((over_x-radius)/SCALE+Gdx.graphics.getWidth()/2,(-over_y-radius)/SCALE+Gdx.graphics.getHeight()/2,(radius*2)/SCALE,(radius*2)/SCALE);
        shape.end();
        int getY = Gdx.graphics.getHeight() - Gdx.input.getY();
        if(Gdx.input.isTouched()) {
            if(!just) {
                if (Gdx.input.getX() > ((over_x - radius) / SCALE + Gdx.graphics.getWidth() / 2) && Gdx.input.getX() < ((over_x - radius) / SCALE + Gdx.graphics.getWidth() / 2) + (radius * 2) / SCALE) {
                    if (getY > ((-over_y - radius) / SCALE + Gdx.graphics.getHeight() / 2) && getY < ((-over_y - radius) / SCALE + Gdx.graphics.getHeight() / 2) + (radius * 2) / SCALE) {
                        System.out.println(toString());
                        System.out.println((Gdx.input.getX()-Gdx.graphics.getWidth() / 2)*SCALE+" "+((getY-Gdx.graphics.getHeight() / 2)*SCALE));
                    }
                }
            }
            just = true;
        }else{
            just = false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCity() {
        return isCity;
    }

    public void setCity(boolean city) {
        isCity = city;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getOver_x() {
        return over_x;
    }

    public void setOver_x(int over_x) {
        this.over_x = over_x;
    }

    public int getOver_y() {
        return over_y;
    }

    public void setOver_y(int over_y) {
        this.over_y = over_y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getNether_x() {
        return nether_x;
    }

    public void setNether_x(int nether_x) {
        this.nether_x = nether_x;
    }

    public int getNether_y() {
        return nether_y;
    }

    public void setNether_y(int nether_y) {
        this.nether_y = nether_y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isJust() {
        return just;
    }

    public void setJust(boolean just) {
        this.just = just;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isCity=" + isCity +
                ", owner='" + owner + '\'' +
                ", over_x=" + over_x +
                ", over_y=" + over_y +
                ", radius=" + radius +
                ", nether_x=" + nether_x +
                ", nether_y=" + nether_y +
                ", color=" + color +
                '}';
    }
}
