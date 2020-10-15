package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseModelTest {
    private BaseModel baseModel;
    private Cell cell;

    @BeforeEach
    public void initBaseModel() {
        baseModel = new BaseModel();
        GameMap gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        cell = new Cell(gameMap, 3, 3, CellType.FLOOR);
    }

    @Test
    public void intValue() {
        int expectedId = 1;

        baseModel.setId(expectedId);

        assertEquals(expectedId, baseModel.getId());
    }

    @Test
    public void toString_baseModelId_stringWithGeneratedIdFieldAndItsValue() {
        String expectedString = "id:1,";
        int expectedId = 1;

        baseModel.setId(expectedId);

        assertEquals(expectedString, baseModel.toString());
    }

    @Test
    public void toString_baseModelIsNotInitialized_illegalAccessExceptionExpected() {
        // TODO implement catch illegalAccessException
//        Potion potion = new Potion(cell);
//        ItemModel itemModel = new ItemModel(potion);
//        Class<IllegalAccessException> expectedException = IllegalAccessException.class;
//        itemModel.getClass().getDeclaredField("id").setAccessible(false);
////        baseModel.setId(1);
//        Assertions.assertThrows(expectedException, () -> baseModel.toString());
    }
}