package places;

import com.darkj24.ioc.annotations.*;
import people.Owner;
import people.Visitor;
import places.beach.Restaurant;

import java.util.ArrayList;
import java.util.List;

@Bean
@Singleton
public class Beach {

    private Owner owner;
    private List<Restaurant> restaurants;
    private List<Visitor> visitors;
    private Double swimmingCost;

    @Autowired
    public Beach() {
        this.visitors = new ArrayList<Visitor>();
        this.swimmingCost = Math.random() * 25;
    }

    @Required
    @Qualifier("beachOwner")
    public void setOwner(Owner owner){
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void newVisitor(Visitor visitor) {
        this.visitors.add(visitor);
    }

    public void removeVisitor(Visitor visitor){
        this.visitors.remove(visitor);
    }

    public Double getSwimmingCost() {
        return swimmingCost;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
