package DesignPatterns.CreationalPattern.BuilderPattern.PizzaBuilderV2;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {
    private String dough;
    private String sauce;
    private List<String> topping;

    public PizzaBuilder() {
        this.topping = new ArrayList<>();
    }

    public PizzaBuilder dough(String dough){
        this.dough = dough;
        return this;
    }
    public PizzaBuilder sauce(String sauce){
        this.sauce = sauce;
        return this;
    }
    public PizzaBuilder addTopping(String topping){
        this.topping.add(topping);
        return this;
    }
    public Pizza build(){
        Pizza pizza = new Pizza(this.dough,this.sauce,this.topping);
        return pizza;
    }
}
