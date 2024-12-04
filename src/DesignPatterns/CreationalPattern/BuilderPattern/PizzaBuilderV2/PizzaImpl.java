package DesignPatterns.CreationalPattern.BuilderPattern.PizzaBuilderV2;

public class PizzaImpl {
    public static void main(String[] args) {
        Pizza pizza = new Pizza.Builder().addTopping("Tomato").addTopping("Onion").dough("Thin Crust").sauce("Alfredo").build();
        float pizzaPrice = new Pizza.PriceBuilder().setDoughPrice(Float.parseFloat("1.99")).setSaucePrice(Float.parseFloat("2.99")).setToppingPrice(Float.parseFloat("2.99")).getPizzaPrice(pizza);
        System.out.println(pizzaPrice);
    }
}
