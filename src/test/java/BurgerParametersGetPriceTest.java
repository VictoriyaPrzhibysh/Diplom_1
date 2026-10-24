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
    private Ingredient ingredientOneMock;
    private Ingredient ingredientTwoMock;

    private float bunPrice;
    private float ingredientOnePrice;
    private float ingredientTwoPrice;
    private float expectedPrice;

    public BurgerParametersGetPriceTest(float bunPrice, float ingredientOnePrice, float ingredientTwoPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientOnePrice = ingredientOnePrice;
        this.ingredientTwoPrice = ingredientTwoPrice;
        this.expectedPrice = expectedPrice;
        }

    @Before
    public void init() {

        // Создаем моки вместо реальных объектов
        bunMock = mock(Bun.class);
        ingredientOneMock = mock(Ingredient.class);
        ingredientTwoMock = mock(Ingredient.class);

        // Настраиваем моки
        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(bunMock.getName()).thenReturn("Test Bun");

        when(ingredientOneMock.getPrice()).thenReturn(ingredientOnePrice);
        when(ingredientOneMock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientOneMock.getName()).thenReturn("Test Sauce");

        when(ingredientTwoMock.getPrice()).thenReturn(ingredientTwoPrice);
        when(ingredientTwoMock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientTwoMock.getName()).thenReturn("Test Filling");

        burger = new Burger();
        burger.setBuns(bunMock);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredientOneMock);
        ingredients.add(ingredientTwoMock);

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
