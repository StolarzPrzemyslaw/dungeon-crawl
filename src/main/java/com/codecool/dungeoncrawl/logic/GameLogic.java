package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.characters.Enemy;
import com.codecool.dungeoncrawl.logic.actors.characters.Person;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.logic.actors.components.Combat;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.view.Game;
import javafx.scene.input.KeyEvent;

public class GameLogic {

    private Game ui;
    private GameMap map = MapLoader.loadMap();
    private Combat combat = new Combat();

    public GameLogic(Game game, String playerName) {
        this.ui = game;
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
        }
    }

    private void playerTurn(int dx, int dy) {
        Cell playerCell = map.getPlayer().getCell();
        Player player = map.getPlayer();
        Cell nextCell = map.getPlayer().getCell().getNeighbor(dx, dy);
        Actor nearActor = map.getPlayer().getCell().getNeighbor(dx, dy).getActor();

        if (nextCell.isOccupiedByClass(Enemy.class)) {
            combat.simulateCombat(player, (Person) nearActor);
            if (map.getPlayer().getHealth() <= 0) {
                ui.generateLoseScreen(player, (Person) nearActor);
            }
        } else if (nextCell.isMovePossible()) {
            player.move(dx, dy);
        }
        ui.refresh();
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
