package DesignPatterns.CreationalPattern.BuilderPattern.PizzaBuilderV2;

public class PizzaPriceCalculator {

    private float doughPrice;
    private float toppingPrice;
    private float saucePrice;

    public PizzaPriceCalculator setDoughPrice(float doughPrice){
        this.doughPrice = doughPrice;
        return this;
    }
    public PizzaPriceCalculator setToppingPrice(float toppingPrice){
        this.toppingPrice = toppingPrice;
        return this;
    }
    public PizzaPriceCalculator setSaucePrice(float saucePrice){
        this.saucePrice = saucePrice;
        return this;
    }

    public float getPizzaPrice(Pizza pizza){
        float price = 0;
        price += doughPrice;
        System.out.println("Dough price: " + doughPrice);
        price += toppingPrice*(pizza.getTopping().size());
        System.out.println("Topping price: " + toppingPrice*(pizza.getTopping().size()));
        price += saucePrice;
        System.out.println("Sauce price: " + saucePrice);
        return price;
    }

}
