package com.codecool.dungeoncrawl.logic.actors.components;

import com.codecool.dungeoncrawl.logic.actors.characters.Enemy;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.logic.actors.characters.Skeleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CombatTest {

    @Test
    public void simulateCombat_validPlayerValidEnemy_CombatDone() {
        // arrange
        Combat combat = new Combat(null);
        Player player = new Player(null);
        Enemy enemy = new Skeleton(null);

        // act
        combat.simulateCombat(player, enemy);

        // assert
        assertEquals(16, player.getCurrentHealth());
        assertEquals(5, enemy.getCurrentHealth());
    }

    @Test
    public void simulateCombat_validPlayerValidEnemy_EnemyDestroyed() {
        // arrange
        Combat combat = new Combat(null);
        Player player = new Player(null);
        Enemy enemy = new Skeleton(null);
        enemy.setCurrentHealth(5);

        // act
        combat.simulateCombat(player, enemy);

        // assert
        assertEquals(16, player.getCurrentHealth());
        assertEquals(0, enemy.getCurrentHealth());
        assertNull(enemy.getCell());
    }
}
