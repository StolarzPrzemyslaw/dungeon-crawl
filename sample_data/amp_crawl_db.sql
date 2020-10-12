DROP TABLE IF EXISTS "public"."game_state";
DROP TABLE IF EXISTS "public"."map";
DROP TABLE IF EXISTS "public"."player";
DROP TABLE IF EXISTS "public"."inventory";
DROP TABLE IF EXISTS "public"."item";
DROP TYPE IF EXISTS item_type;

CREATE TYPE item_type AS ENUM ('usable', 'consumable', 'none');

-- ************************************** "public"."item"

CREATE TABLE "public"."item"
(
 "id"        serial NOT NULL,
 "name"      text NOT NULL,
 "statistic" int NOT NULL,
 "type"      item_type NOT NULL,
 CONSTRAINT "PK_item" PRIMARY KEY ( "id" )
);

-- ************************************** "public"."inventory"

CREATE TABLE "public"."inventory"
(
 "id"      serial NOT NULL,
 "item_id" integer NOT NULL,
 CONSTRAINT "PK_inventory" PRIMARY KEY ( "id" ),
 CONSTRAINT "FK_51" FOREIGN KEY ( "item_id" ) REFERENCES "public"."item" ( "id" )
);

CREATE INDEX "fkIdx_51" ON "public"."inventory"
(
 "item_id"
);

-- ************************************** "public"."player"

CREATE TABLE "public"."player"
(
 "id"             serial NOT NULL,
 "health"         int NOT NULL,
 "current_health" int NOT NULL,
 "strength"       int NOT NULL,
 "name"           text NOT NULL,
 "posX"           int NOT NULL,
 "posY"           int NOT NULL,
 "inventory_id"   integer NOT NULL,
 "weapon_id"      integer NOT NULL,
 CONSTRAINT "PK_player" PRIMARY KEY ( "id" ),
 CONSTRAINT "FK_28" FOREIGN KEY ( "inventory_id" ) REFERENCES "public"."inventory" ( "id" ),
 CONSTRAINT "FK_66" FOREIGN KEY ( "weapon_id" ) REFERENCES "public"."item" ( "id" )
);

CREATE INDEX "fkIdx_28" ON "public"."player"
(
 "inventory_id"
);

CREATE INDEX "fkIdx_66" ON "public"."player"
(
 "weapon_id"
);

-- ************************************** "public"."map"

CREATE TABLE "public"."map"
(
 "id"        int NOT NULL,
 "file_name" text NOT NULL,
 CONSTRAINT "PK_map" PRIMARY KEY ( "id" )
);

-- ************************************** "public"."game_state"

CREATE TABLE "public"."game_state"
(
 "id"          serial NOT NULL,
 "saved_at"    date NOT NULL,
 "player_id"   integer NOT NULL,
 "current_map" int NOT NULL,
 "save_name"   text NOT NULL,
 CONSTRAINT "PK_game_state" PRIMARY KEY ( "id" ),
 CONSTRAINT "FK_22" FOREIGN KEY ( "player_id" ) REFERENCES "public"."player" ( "id" ),
 CONSTRAINT "FK_76" FOREIGN KEY ( "current_map" ) REFERENCES "public"."map" ( "id" )
);

CREATE INDEX "fkIdx_22" ON "public"."game_state"
(
 "player_id"
);

CREATE INDEX "fkIdx_76" ON "public"."game_state"
(
 "current_map"
);

INSERT INTO map (id, file_name) VALUES (1, 'level1.txt');
INSERT INTO map (id, file_name) VALUES (2, 'level2.txt');
INSERT INTO map (id, file_name) VALUES (3, 'level3.txt');