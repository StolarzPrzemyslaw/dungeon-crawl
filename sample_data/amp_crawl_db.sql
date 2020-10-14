DROP TABLE IF EXISTS public.game_state CASCADE;
DROP TABLE IF EXISTS public.player CASCADE;
DROP TABLE IF EXISTS public.inventory CASCADE;
DROP TABLE IF EXISTS public.item CASCADE;

ALTER TABLE IF EXISTS ONLY public.game_state
    DROP CONSTRAINT IF EXISTS fk_player_id_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.player
    DROP CONSTRAINT IF EXISTS fk_inventory_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.item
    DROP CONSTRAINT IF EXISTS fk_player_inventory_id CASCADE;

CREATE TABLE item
(
    "id"                  serial    NOT NULL PRIMARY KEY,
    "player_inventory_id" integer   NOT NULL,
    "name"                text      NOT NULL,
    "statistic"           int       NOT NULL,
    "type"                text      NOT NULL
);

CREATE TABLE inventory
(
    "id"                  serial  NOT NULL PRIMARY KEY,
    "player_inventory_id" integer NOT NULL
);

CREATE TABLE player
(
    "id"             serial  NOT NULL PRIMARY KEY,
    "health"         int     NOT NULL,
    "current_health" int     NOT NULL,
    "strength"       int     NOT NULL,
    "name"           text    NOT NULL,
    "pos_x"           int     NOT NULL,
    "pos_y"           int     NOT NULL,
    "inventory_id"   integer NOT NULL,
    "weapon_id"      integer NOT NULL
);

CREATE TABLE game_state
(
    "id"          serial  NOT NULL PRIMARY KEY,
    "saved_at"    date    NOT NULL,
    "player_id"   integer NOT NULL,
    "current_map" int     NOT NULL,
    "save_name"   text    NOT NULL
);

ALTER TABLE ONLY game_state
    ADD CONSTRAINT fk_player_id_id FOREIGN KEY (player_id) REFERENCES player (id);

ALTER TABLE ONLY player
    ADD CONSTRAINT fk_inventory_id FOREIGN KEY (inventory_id) REFERENCES inventory (id);

ALTER TABLE ONLY item
    ADD CONSTRAINT fk_player_inventory_id FOREIGN KEY (player_inventory_id) REFERENCES inventory (id);
