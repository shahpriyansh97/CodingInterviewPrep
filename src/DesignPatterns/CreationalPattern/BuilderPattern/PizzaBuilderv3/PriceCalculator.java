package DesignPatterns.CreationalPattern.BuilderPattern.PizzaBuilderv3;

import java.math.BigDecimal;

public interface PriceCalculator {

    BigDecimal calculatePrice(Pizza pizza);
}
