package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.actors.characters.Player;

@FunctionalInterface
public interface Usable {
    void use(Player player);
}
