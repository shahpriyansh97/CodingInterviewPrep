package DesignPatterns.CreationalPattern.BuilderPattern.PizzaBuilderv3;

import java.math.BigDecimal;

public class PizzaApp {

    public static void main(String[] args) {
        // Build a pizza using the Builder pattern
        Pizza pizza = new Pizza.Builder()
                .dough(DoughType.THIN_CRUST)
                .sauce(SauceType.ALFREDO)
                .addTopping(ToppingType.TOMATO)
                .addTopping(ToppingType.ONION)
                .build();

        // Create a price calculator instance
        PizzaPriceCalculator calculator = new PizzaPriceCalculator();

        // Optionally, customize prices
         calculator.setDoughPrice(DoughType.THIN_CRUST, new BigDecimal("1.99"));
         calculator.setSaucePrice(SauceType.ALFREDO, new BigDecimal("2.99"));
         calculator.setToppingPrice(ToppingType.TOMATO, new BigDecimal("0.99"));
         calculator.setToppingPrice(ToppingType.ONION, new BigDecimal("0.99"));

        // Calculate the price of the pizza
        BigDecimal price = calculator.calculatePrice(pizza);

        // Display the total price
        System.out.println("Total pizza price: $" + price);
    }
}
