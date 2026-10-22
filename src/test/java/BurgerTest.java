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
    private Ingredient ingredient1;
@Mock
    private Ingredient ingredient2;

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
    burger.addIngredient(ingredient1);
    burger.addIngredient(ingredient2);
    // проверяем, что список увеличился на 2 ингредиента
    assertEquals(initialIngredientSize + 2, burger.ingredients.size());
}

@Test
    public void testRemoveIngredient() {
    //добавляем два ингредиента
    burger.addIngredient(ingredient1);
    burger.addIngredient(ingredient2);
    // удаляем первый добавленный ингредиент
    burger.removeIngredient(0);
    // проверяем, что первого ингредиента нет в списке
    assertFalse(burger.ingredients.contains(ingredient1));
}

@Test
    public void testMoveIngredient() {
    //добавляем два ингредиента
    burger.addIngredient(ingredient1);
    burger.addIngredient(ingredient2);
    // переносим второй ингредиент на место первого
    burger.moveIngredient(1, 0);
    // проверяем что второй ингредиент встал на место первого
    assertEquals(ingredient2, burger.ingredients.get(0));
}

}
