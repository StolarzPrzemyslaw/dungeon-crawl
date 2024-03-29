package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.characters.*;
import com.codecool.dungeoncrawl.logic.actors.components.Combat;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.logic.actors.obstacles.Stairs;
import com.codecool.dungeoncrawl.model.GameStateModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.codecool.dungeoncrawl.view.FileChooserWindow;
import com.codecool.dungeoncrawl.view.Game;
import com.codecool.dungeoncrawl.view.SaveGameModal;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameLogic {

    private final Game ui;
    private GameMap map = MapLoader.loadMap(Map.LEVEL1, true);
    private final Combat combat;
    private final boolean cheatsEnabled;
    GameDatabaseManager dbManager;
    private final boolean isImportAction = true;

    public GameLogic(Game game, String playerName, GameDatabaseManager dbManager) {
        this.ui = game;
        this.combat = new Combat(ui);
        this.dbManager = dbManager;
        cheatsEnabled = playerName.equals("Andrzej") || playerName.equals("Marcin") || playerName.equals("Przemysław");
        map.getPlayer().setPlayerName(playerName);
    }

    public void loadGameFromState(GameStateModel gameState) {
        Map mapToLoad = gameState.getCurrentMap();
        map = MapLoader.loadMap(mapToLoad, false);
        PlayerModel playerModel = gameState.getPlayer();
        Cell playerCell = map.getCell(playerModel.getX(), playerModel.getY());
        playerModel.setPlayerCell(playerCell);
        map.setPlayer(playerModel.getPlayer());

        ui.setMap(map);
        ui.displayLog("");
        ui.displayLog("You have loaded GameState!");
        ui.refresh();
    }

    public void onKeyReleased(KeyEvent keyEvent) throws IOException {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);

        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        } else {
            triggerKeyPressedAction(keyEvent);
        }
    }

    private void triggerKeyPressedAction(KeyEvent keyEvent) throws IOException {
        switch (keyEvent.getCode()) {
            case F5:
                if (isPlayerAbleToSaveGame()) saveGameToDatabase();
                else showCantSaveGameMessage();
                break;
            case F6:
                if (isPlayerAbleToSaveGame()) exportGameState();
                else showCantSaveGameMessage();
                break;
            case F7:
                importGameState();
                break;
        }
    }

    private void exportGameState() throws IOException {
        new FileChooserWindow(ui.getMain().getStage(), !isImportAction, ui).importExportFile();
    }

    private void importGameState() throws IOException {
        new FileChooserWindow(ui.getMain().getStage(), isImportAction, ui).importExportFile();
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
        Player player = map.getPlayer();
        Cell nextCell = map.getPlayer().getCell().getNeighbor(dx, dy);
        Actor nearActor = map.getPlayer().getCell().getNeighbor(dx, dy).getActor();

        if (isEnemyAffront(nextCell)) {
            combat.simulateCombat(player, (Person) nearActor);
        } else if (areStairsUnderPlayer(nextCell)) {
            loadNextMap();
        } else if (isPlayerAbleToMove(nextCell)) {
            player.move(dx, dy);
            enemiesTurn();
        }
        ui.refresh();
    }

    private boolean isEnemyAffront(Cell nextCell) {
        return nextCell.isOccupiedByClass(Enemy.class);
    }

    private boolean areStairsUnderPlayer(Cell nextCell) {
        return nextCell.isOccupiedByClass(Stairs.class);
    }

    private boolean isPlayerAbleToMove(Cell nextCell) {
        return cheatsEnabled || nextCell.isMovePossible();
    }

    private void loadNextMap() {
        Player temporaryPlayer = map.getPlayer();
        int numberOfMap = map.getLevelId();
        Map nextLevel = Arrays.stream(Map.values())
                .filter(map -> map.getId() == numberOfMap + 1)
                .findFirst()
                .orElse(Map.LEVEL4);

        map = MapLoader.loadMap(nextLevel, true);
        map.getPlayer().setWeapon(temporaryPlayer.getWeapon());
        Cell temporaryCell = map.getPlayer().getCell();
        map.setPlayer(temporaryPlayer);
        map.getPlayer().setPlayerCell(temporaryCell);
        ui.setMap(map);
        ui.displayLog("You have moved to the next level!");
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
        Skeleton skeleton = ((Skeleton) actor);
        List<int[]> directions = getDirectionsOptions(skeleton);
        makeMoveIfMoveExist(skeleton, directions);
    }

    private void makeMoveIfMoveExist(Skeleton skeleton, List<int[]> directions) {
        final int FIRST_COORDINATE = 0;
        final int SECOND_COORDINATE = 1;
        if (directions.size() != 0) {
            int randomDirection = (int) (Math.random() * directions.size());
            int firstCoordinate = directions.get(randomDirection)[FIRST_COORDINATE];
            int secondCoordinate = directions.get(randomDirection)[SECOND_COORDINATE];
            makeEnemyMove(skeleton, firstCoordinate, secondCoordinate);
        }
    }

    private void makeEnemyMove(Skeleton skeleton, int firstCoordinate, int secondCoordinate) {
        if (skeleton.getCell().getNeighbor(firstCoordinate, secondCoordinate).isOccupiedByClass(Player.class)) {
            combat.simulateCombat(skeleton, map.getPlayer());
        } else {
            skeleton.move(firstCoordinate, secondCoordinate);
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
            selectDirectionAndMove(cow);
        }
        if (cow.getStepsLeft() <= 0) {
            swapDirection(cow);
        }
    }

    private void selectDirectionAndMove(Cow cow) {
        if (cow.isMovingLeft()) {
            moveCowOrSwapDirection(cow, -1);
        } else {
            moveCowOrSwapDirection(cow, 1);
        }
    }

    private void moveCowOrSwapDirection(Cow cow, int dx) {
        if (cow.getCell().getNeighbor(dx, 0).isOccupiedByClass(Player.class)) {
            combat.simulateCombat(cow, map.getPlayer());
        } else if (cow.getCell().getNeighbor(dx, 0).isEnemyMovePossible()) {
            moveCowStepAheadHorizontal(cow, dx);
        } else {
            swapDirection(cow);
        }
    }

    private void moveCowStepAheadHorizontal(Cow cow, int dx) {
        cow.decreaseStepsLeft();
        cow.move(dx, 0);
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

    public boolean isPlayerAbleToSaveGame() {
        return map.getPlayer().getCell().isBonfireOneOfTheNeighbours();
    }

    public GameMap getGameMap() {
        return map;
    }

    private void exit() {
        System.exit(1);
    }

    private void saveGameToDatabase() {
        new SaveGameModal(dbManager, map).show();    }

    private void showCantSaveGameMessage() {
        ui.displayLog("You can't save game here! Go to the nearest bonfire.");
    }
}
