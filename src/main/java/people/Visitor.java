package people;

import com.darkj24.ioc.annotations.Autowired;
import com.darkj24.ioc.annotations.Bean;
import com.darkj24.ioc.annotations.Prototype;
import items.Menu;
import places.Beach;
import places.beach.Restaurant;

import java.util.ArrayList;
import java.util.List;

@Bean
@Prototype
public class Visitor {

    private Double money;
    private List<String> actions;
    private boolean needsMoney;
    private String name;
    private int identifier;

    @Autowired
    public Visitor() {
        this.money = Math.random() * 1000;
        this.actions = new ArrayList<String>();
        this.actions.add("Visita la playa con $" + money);
        this.needsMoney = false;
        this.identifier = (int)(Math.random()*25);
        this.name = generateName();
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
        this.name = generateName();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public void gainMoney(Double money){
        this.money += money;
    }

    public void spendMoney(Double money){
        if (this.money > money) {
            this.money -= money;
        } else {
            this.needsMoney = true;
        }
    }

    public void swim(Beach beach){
        this.spendMoney(beach.getSwimmingCost());
        if (!this.needsMoney) {
            beach.getOwner().gainMoney(beach.getSwimmingCost());
            this.actions.add("Se mete a nadar por $" + beach.getSwimmingCost());
        } else {
            this.actions.add("No le alcanzó el dinero para poder nadar");
        }
    }

    public void eat(Restaurant restaurant){
        int me = (int) (Math.random() * restaurant.getMenu().getMeals().size());
        Menu.Meal meal = restaurant.getMenu().getMeals().get(me);
        this.spendMoney(meal.getCost());
        if (!this.needsMoney) {
            restaurant.getOwner().gainMoney(meal.getCost());
            this.actions.add("Come en el restaurante " + restaurant.getName() + " el platillo " + meal.getName() + " por $" + meal.getCost());
        } else {
            this.actions.add("No le alcanzó el dinero para comer en restaurante " + restaurant.getName() + " el platillo " + meal.getName());
        }
    }

    public void leave(){
        if (!this.needsMoney) {
            this.actions.add("Se va de la playa");
        } else {
            this.actions.add("Se va de la playa sin dinero");
        }
    }

    public void comeback(){
        this.actions.add("Retornó a la playa");
        if (Math.random() > 0.5) {
            Double moreMoney = Math.random() * 1000;
            this.actions.add("Ganó más dinero para gastar: $" + moreMoney);
        }
    }

    public void printActions(){
        for (String action: actions){
            System.out.println(name + ": " + action);
        }
    }

    public boolean needsMoney() {
        return needsMoney;
    }

    private String generateName(){
        return "John Doe "+ this.identifier;
    }

}
