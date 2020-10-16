package places.beach;

import com.darkj24.ioc.annotations.*;
import items.Menu;
import people.Owner;

@Bean
@Prototype
@Lazy
public class Restaurant {

    private Menu menu;
    private String name;
    private Owner owner;

    @Autowired
    public Restaurant(@Qualifier("restaurantsOwner") Owner owner) {
        this.owner = owner;
    }

    @Init
    public void init(){
        this.name = generateName();
    }

    @Required
    public void setMenu(Menu menu){
        this.menu = menu;
    }

    public Owner getOwner() {
        return owner;
    }

    public Menu getMenu() {
        return menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String generateName(){
        String[] names = {"Italiano", "Americano", "Oriental", "Típico"};
        return names[(int)(Math.random() * names.length)];
    }

}
