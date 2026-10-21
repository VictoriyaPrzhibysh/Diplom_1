import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)

public class BurgerParametersGetPriceTest {

    private Burger burger;

    // Моки
    private Bun bunMock;
    private Ingredient ingredient1Mock;
    private Ingredient ingredient2Mock;

    private float bunPrice;
    private float ingredient1Price;
    private float ingredient2Price;
    private float expectedPrice;

    public BurgerParametersGetPriceTest(float bunPrice, float ingredient1Price, float ingredient2Price, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredient1Price = ingredient1Price;
        this.ingredient2Price = ingredient2Price;
        this.expectedPrice = expectedPrice;
        }

    @Before
    public void init() {

        // Создаем моки вместо реальных объектов
        bunMock = mock(Bun.class);
        ingredient1Mock = mock(Ingredient.class);
        ingredient2Mock = mock(Ingredient.class);

        // Настраиваем моки
        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(bunMock.getName()).thenReturn("Test Bun");

        when(ingredient1Mock.getPrice()).thenReturn(ingredient1Price);
        when(ingredient1Mock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1Mock.getName()).thenReturn("Test Sauce");

        when(ingredient2Mock.getPrice()).thenReturn(ingredient2Price);
        when(ingredient2Mock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2Mock.getName()).thenReturn("Test Filling");

        burger = new Burger();
        burger.setBuns(bunMock);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient1Mock);
        ingredients.add(ingredient2Mock);

        for (Ingredient ing : ingredients) {
            burger.addIngredient(ing);
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // булочка 100 * 2 = 200
                {100f, 0f, 0f, 200f},
                // булочка 100 * 2 + ингрединет 50 = 250
                {100f, 50f, 0f, 250f},
                // булочка 100 * 2 + иггредиент 50 + ингредиент 40 = 290
                {100f, 50f, 40f, 290f}
        });
    }

    @Test
    public void testGetPrice() {
        float actualPrice = burger.getPrice();

        // проверяем что ожидаемая цена равна фактической, с погрешностью 0,01
        assertEquals(expectedPrice, actualPrice, 0.01f);
    }

}
