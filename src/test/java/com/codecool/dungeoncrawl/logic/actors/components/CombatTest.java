package com.codecool.dungeoncrawl.logic.actors.components;

import com.codecool.dungeoncrawl.logic.actors.characters.Enemy;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.logic.actors.characters.Skeleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombatTest {

    private static Combat combat;
    private static Player player;
    private static Enemy enemy;

    @BeforeEach
    public void prepareCombat() {
        combat = new Combat(null);
        player = new Player(null);
        enemy = new Skeleton(null);
    }

    @Test
    public void simulateCombat_validCombat_playerTakesDamage() {
        combat.simulateCombat(player, enemy);
        assertEquals(16, player.getCurrentHealth());
    }

    @Test
    public void simulateCombat_validCombat_enemyTakesDamage() {
        combat.simulateCombat(player, enemy);
        assertEquals(5, enemy.getCurrentHealth());
    }

    @Test
    public void simulateCombat_validCombat_enemyDestroyed() {
        enemy.setCurrentHealth(5);
        combat.simulateCombat(player, enemy);
        assertNull(enemy.getCell());
    }

    @Test
    public void simulateCombat_validCombat_playerDestroyed() {
        player.setCurrentHealth(4);
        combat.simulateCombat(player, enemy);
        assertNull(player.getCell());
    }

    @Test
    public void simulateCombat_validCombat_playerLives() {
        combat.simulateCombat(player, enemy);
        assertTrue(player.getCurrentHealth() > 0);
    }
}
