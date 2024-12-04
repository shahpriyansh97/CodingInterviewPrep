package DesignPatterns.CreationalPattern.BuilderPattern.PizzaBuilderv3;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pizza {
    private final DoughType dough;
    private final SauceType sauce;
    private final List<ToppingType> toppings;

    private Pizza(Builder builder) {
        this.dough = builder.dough;
        this.sauce = builder.sauce;
        this.toppings = Collections.unmodifiableList(new ArrayList<>(builder.toppings));
    }

    /**
     * Gets the dough type of the pizza.
     * @return the dough type
     */
    public DoughType getDough() {
        return dough;
    }

    /**
     * Gets the sauce type of the pizza.
     * @return the sauce type
     */
    public SauceType getSauce() {
        return sauce;
    }

    /**
     * Gets an unmodifiable list of toppings on the pizza.
     * @return the list of toppings
     */
    public List<ToppingType> getToppings() {
        return toppings;
    }

    /**
     * Builder class for constructing Pizza instances.
     */
    public static class Builder {
        private DoughType dough;
        private SauceType sauce;
        private List<ToppingType> toppings = new ArrayList<>();

        /**
         * Sets the dough type for the pizza.
         * @param dough the type of dough
         * @return the builder instance
         */
        public Builder dough(DoughType dough) {
            if (dough == null) {
                throw new IllegalArgumentException("Dough type cannot be null");
            }
            this.dough = dough;
            return this;
        }

        /**
         * Sets the sauce type for the pizza.
         * @param sauce the type of sauce
         * @return the builder instance
         */
        public Builder sauce(SauceType sauce) {
            if (sauce == null) {
                throw new IllegalArgumentException("Sauce type cannot be null");
            }
            this.sauce = sauce;
            return this;
        }

        /**
         * Adds a topping to the pizza.
         * @param topping the topping to add
         * @return the builder instance
         */
        public Builder addTopping(ToppingType topping) {
            if (topping == null) {
                throw new IllegalArgumentException("Topping cannot be null");
            }
            this.toppings.add(topping);
            return this;
        }

        /**
         * Builds and returns the Pizza instance.
         * @return the constructed Pizza
         */
        public Pizza build() {
            if (dough == null) {
                throw new IllegalStateException("Dough type must be specified");
            }
            if (sauce == null) {
                throw new IllegalStateException("Sauce type must be specified");
            }
            return new Pizza(this);
        }
    }
}