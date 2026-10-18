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

@RunWith(Parameterized.class)

public class BurgerTestParametersGetReceipt {

    private Burger burger;

    private float bunPrice;
    private float ingredient1Price;
    private float ingredient2Price;
    private String expectedReceipt;

    public BurgerTestParametersGetReceipt(float bunPrice, float ingredient1Price, float ingredient2Price, String expectedReceipt) {
        this.bunPrice = bunPrice;
        this.ingredient1Price = ingredient1Price;
        this.ingredient2Price = ingredient2Price;
        this.expectedReceipt = expectedReceipt;
    }

    @Before
    public void init() {
        Bun bun = new Bun("Test Bun", bunPrice);

        List<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(new Ingredient(IngredientType.SAUCE, "Test Sauce", ingredient1Price));
        ingredients.add(new Ingredient(IngredientType.FILLING, "Test Filling", ingredient2Price));

        burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ing : ingredients) {
            burger.addIngredient(ing);
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{
                        // булочка 100 * 2 = 200
                        100f, 0f, 0f,
                        "(==== Test Bun ====)\n" +
                                "= sauce Test Sauce =\n" +
                                "= filling Test Filling =\n" +
                                "(==== Test Bun ====)\n\n" +
                                "Price: 200,000000\n"
                },
                new Object[]{
                        // булочка 100 * 2 + ингрединет 50 = 250
                        100f, 50f, 0f,
                        "(==== Test Bun ====)\n" +
                                "= sauce Test Sauce =\n" +
                                "= filling Test Filling =\n" +
                                "(==== Test Bun ====)\n\n" +
                                "Price: 250,000000\n"
                },
                new Object[]{
                        // булочка 100 * 2 + иггредиент 50 + ингредиент 40 = 290
                        100f, 50f, 40f,
                        "(==== Test Bun ====)\n" +
                                "= sauce Test Sauce =\n" +
                                "= filling Test Filling =\n" +
                                "(==== Test Bun ====)\n\n" +
                                "Price: 290,000000\n"
                }
        );
    }

    @Test
    public void testGetReceipt() {
        String actualReceipt = burger.getReceipt();

        String normalizedActual = actualReceipt.replace("\r\n", "\n").replace("\r", "\n");

        assertEquals(expectedReceipt, normalizedActual);
    }
}
