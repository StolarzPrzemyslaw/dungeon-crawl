package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.characters.*;
import com.codecool.dungeoncrawl.logic.actors.components.Combat;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.view.Game;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    private Game ui;
    private GameMap map = MapLoader.loadMap();
    private Combat combat;

    public GameLogic(Game game, String playerName) {
        this.ui = game;
        this.combat = new Combat(ui);
        map.getPlayer().setPlayerName(playerName);
    }

    public GameLogic(Game game, String playerName, GameMap gameMap) {
        this(game, playerName);
        this.map = gameMap;
    }

    public GameMap getGameMap() {
        return map;
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                playerTurn(0, -1);
                break;
            case S:
                playerTurn(0, 1);
                break;
            case A:
                playerTurn(-1, 0);
                break;
            case D:
                playerTurn(1, 0);
                break;
            case F:
                playerTurn(0, 0);
                break;
        }
    }

    private void playerTurn(int dx, int dy) {
        Cell playerCell = map.getPlayer().getCell();
        Player player = map.getPlayer();
        Cell nextCell = map.getPlayer().getCell().getNeighbor(dx, dy);
        Actor nearActor = map.getPlayer().getCell().getNeighbor(dx, dy).getActor();

        if (nextCell.isOccupiedByClass(Enemy.class)) {
            combat.simulateCombat(player, (Person) nearActor);
            if (map.getPlayer().getCurrentHealth() <= 0) {
                ui.generateLoseScreen(player, (Person) nearActor);
            }
        } else if (nextCell.isMovePossible()) {
            player.move(dx, dy);
            enemiesTurn();
        }
        ui.refresh();
    }

    private void enemiesTurn() {
        moveAllSkeletons();
        moveAllCows();
    }

    private void moveAllSkeletons() {
        List<Actor> skeletons = map.getAllEnemiesOnMap(Skeleton.class);
        skeletons.forEach(this::validateCoordinatesInEveryDirection);
    }

    private void validateCoordinatesInEveryDirection(Actor actor) {
        final int FIRST_COORDINATE = 0;
        final int SECOND_COORDINATE = 1;
        Skeleton skeleton = ((Skeleton) actor);

        List<int[]> directions = getDirectionsOptions(skeleton);
        if (directions.size() != 0) {
            int randomDirection = (int) (Math.random() * directions.size());
            skeleton.move(directions.get(randomDirection)[FIRST_COORDINATE], directions.get(randomDirection)[SECOND_COORDINATE]);
        }
    }

    private List<int[]> getDirectionsOptions(Skeleton skeleton) {
        List<int[]> directions = new ArrayList<>();
        checkMoveAndAdd(skeleton.getCell(), directions, 0, -1);
        checkMoveAndAdd(skeleton.getCell(), directions, -1, 0);
        checkMoveAndAdd(skeleton.getCell(), directions, 0, 1);
        checkMoveAndAdd(skeleton.getCell(), directions, 1, 0);
        return directions;
    }

    private void checkMoveAndAdd(Cell cell, List<int[]> directions, int dx, int dy) {
        if (cell.getNeighbor(dx, dy).isEnemyMovePossible()) {
            directions.add(new int[]{dx, dy});
        }
    }

    private void moveAllCows() {
        List<Actor> cows = map.getAllEnemiesOnMap(Cow.class);
        cows.forEach(this::validateCowMove);
    }

    private void validateCowMove(Actor actor) {
        Cow cow = ((Cow) actor);
        if (cow.getStepsLeft() > 0) {
            if (cow.isMovingLeft()) {
                moveCowOrSwapDirection(cow, -1, 0);
            } else {
                moveCowOrSwapDirection(cow, 1, 0);
            }
        }
        if (cow.getStepsLeft() <= 0) {
            swapDirection(cow);
        }
    }

    private void moveCowOrSwapDirection(Cow cow, int dx, int dy) {
        if (cow.getCell().getNeighbor(dx, dy).isMovePossible()) {
            cow.decreaseStepsLeft();
            cow.move(dx, dy);
        } else {
            swapDirection(cow);
        }
    }

    private void swapDirection(Cow cow) {
        cow.resetStepsLeft();
        cow.swapDirectionOfMoving();
    }

    public boolean isPlayerStandingOnItem() {
        return map.getPlayer().getBackgroundCellActor() instanceof Item;
    }

    public boolean isPlayerHoldingAKey() {
        return map.getPlayer().getInventory().contains("Key");
    }

    public boolean isPlayerNearDoor() {
        return map.getPlayer().getCell().isDoorOneOfTheNeighbors();
    }
}
