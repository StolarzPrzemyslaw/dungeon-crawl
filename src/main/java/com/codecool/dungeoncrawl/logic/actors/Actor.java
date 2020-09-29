package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.components.Combat;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected Combat combat = new Combat();
    protected int health = 10;
    protected int strength = 5;
    protected String name = "";


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public String getName() {
        return name;
    }
}
