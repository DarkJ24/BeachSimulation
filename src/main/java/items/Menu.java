package items;

import com.darkj24.ioc.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Bean
@Prototype
@Lazy
public class Menu {

    private List<Meal> meals;

    @Autowired
    public Menu() {
        this.meals = new ArrayList<Meal>();
    }

    @Init
    public void init(){
        int count = (int) (Math.random() * 25 + 1);
        for(int m=1; m <= count; m++){
            this.meals.add(new Meal(m));
        }
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public class Meal {

        private Double cost;
        private String name;

        public Meal(int number) {
            this.cost = Math.random() * 50;
            this.name = generateName() + " " + number;
        }

        private String generateName(){
            String[] names = {"Rice & Beans", "Casado", "Sopa", "Pulpo", "Tortuga"};
            return names[(int)(Math.random() * names.length)];
        }

        public Double getCost() {
            return cost;
        }

        public void setCost(Double cost) {
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
