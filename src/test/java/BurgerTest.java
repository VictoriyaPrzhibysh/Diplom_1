import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class BurgerTest {

@Mock
    private Bun bun;
@Mock
    private Ingredient ingredientOne;
@Mock
    private Ingredient ingredientTwo;

    private Burger burger;

@Before
    public void setUp() {
        burger = new Burger();
    }

@Test
    public void testAddIngredient() {
    // текущий размер списка
    int initialIngredientSize = burger.ingredients.size();
    //добавляем два ингредиента
    burger.addIngredient(ingredientOne);
    burger.addIngredient(ingredientTwo);
    // проверяем, что список увеличился на 2 ингредиента
    assertEquals(initialIngredientSize + 2, burger.ingredients.size());
}

@Test
    public void testRemoveIngredient() {
    //добавляем два ингредиента
    burger.addIngredient(ingredientOne);
    burger.addIngredient(ingredientTwo);
    // удаляем первый добавленный ингредиент
    burger.removeIngredient(0);
    // проверяем, что первого ингредиента нет в списке
    assertFalse(burger.ingredients.contains(ingredientOne));
}

@Test
    public void testMoveIngredient() {
    //добавляем два ингредиента
    burger.addIngredient(ingredientOne);
    burger.addIngredient(ingredientTwo);
    // переносим второй ингредиент на место первого
    burger.moveIngredient(1, 0);
    // проверяем что второй ингредиент встал на место первого
    assertEquals(ingredientTwo, burger.ingredients.get(0));
}

}
