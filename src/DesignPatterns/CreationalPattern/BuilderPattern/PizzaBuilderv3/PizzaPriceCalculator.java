package DesignPatterns.CreationalPattern.BuilderPattern.PizzaBuilderv3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Calculates the price of a pizza based on its components.
 */
public class PizzaPriceCalculator implements PriceCalculator {

    private final Map<DoughType, BigDecimal> doughPrices = new HashMap<>();
    private final Map<SauceType, BigDecimal> saucePrices = new HashMap<>();
    private final Map<ToppingType, BigDecimal> toppingPrices = new HashMap<>();

    /**
     * Initializes the price calculator with default prices.
     */
    public PizzaPriceCalculator() {
        // Set default prices for dough types
        doughPrices.put(DoughType.THIN_CRUST, new BigDecimal("2.00"));
        doughPrices.put(DoughType.THICK_CRUST, new BigDecimal("2.50"));
        doughPrices.put(DoughType.GLUTEN_FREE, new BigDecimal("3.00"));

        // Set default prices for sauce types
        saucePrices.put(SauceType.TOMATO, new BigDecimal("1.00"));
        saucePrices.put(SauceType.ALFREDO, new BigDecimal("1.50"));
        saucePrices.put(SauceType.PESTO, new BigDecimal("1.75"));

        // Set default prices for toppings
        toppingPrices.put(ToppingType.ONION, new BigDecimal("0.50"));
        toppingPrices.put(ToppingType.TOMATO, new BigDecimal("0.50"));
        toppingPrices.put(ToppingType.PEPPERONI, new BigDecimal("1.00"));
        toppingPrices.put(ToppingType.MUSHROOMS, new BigDecimal("0.75"));
        toppingPrices.put(ToppingType.OLIVES, new BigDecimal("0.65"));
        toppingPrices.put(ToppingType.CHEESE, new BigDecimal("0.80"));
    }

    /**
     * Calculates the price of the given pizza.
     * @param pizza the pizza to calculate the price for
     * @return the total price as BigDecimal
     */
    @Override
    public BigDecimal calculatePrice(Pizza pizza) {
        BigDecimal price = BigDecimal.ZERO;

        BigDecimal doughPrice = doughPrices.get(pizza.getDough());
        if (doughPrice == null) {
            throw new IllegalArgumentException("Price not set for dough type: " + pizza.getDough());
        }
        price = price.add(doughPrice);

        BigDecimal saucePrice = saucePrices.get(pizza.getSauce());
        if (saucePrice == null) {
            throw new IllegalArgumentException("Price not set for sauce type: " + pizza.getSauce());
        }
        price = price.add(saucePrice);

        for (ToppingType topping : pizza.getToppings()) {
            BigDecimal toppingPrice = toppingPrices.get(topping);
            if (toppingPrice == null) {
                throw new IllegalArgumentException("Price not set for topping: " + topping);
            }
            price = price.add(toppingPrice);
        }

        return price;
    }

    /**
     * Sets the price for a specific dough type.
     * @param doughType the dough type
     * @param price the price to set
     */
    public void setDoughPrice(DoughType doughType, BigDecimal price) {
        if (doughType == null || price == null) {
            throw new IllegalArgumentException("Dough type and price cannot be null");
        }
        doughPrices.put(doughType, price);
    }

    /**
     * Sets the price for a specific sauce type.
     * @param sauceType the sauce type
     * @param price the price to set
     */
    public void setSaucePrice(SauceType sauceType, BigDecimal price) {
        if (sauceType == null || price == null) {
            throw new IllegalArgumentException("Sauce type and price cannot be null");
        }
        saucePrices.put(sauceType, price);
    }

    /**
     * Sets the price for a specific topping type.
     * @param toppingType the topping type
     * @param price the price to set
     */
    public void setToppingPrice(ToppingType toppingType, BigDecimal price) {
        if (toppingType == null || price == null) {
            throw new IllegalArgumentException("Topping type and price cannot be null");
        }
        toppingPrices.put(toppingType, price);
    }
}