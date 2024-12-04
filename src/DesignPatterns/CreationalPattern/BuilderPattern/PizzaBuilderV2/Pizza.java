package DesignPatterns.CreationalPattern.BuilderPattern.PizzaBuilderV2;

import java.util.List;

public class Pizza {
    private String dough;
    private String sauce;
    private List<String> topping;

    public Pizza(String dough, String sauce, List<String> topping) {
        this.dough = dough;
        this.sauce = sauce;
        this.topping = topping;
    }

    public String getDough() {
        return dough;
    }

    public void setDough(String dough) {
        this.dough = dough;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public List<String> getTopping() {
        return topping;
    }

    public void addTopping(String topping) {
        this.topping.add(topping);
    }

    public static class Builder extends PizzaBuilder {}

    public static class PriceBuilder extends PizzaPriceCalculator {}

}
