import com.darkj24.ioc.DependencyInjector;
import com.darkj24.ioc.services.Container;
import items.Menu;
import people.Visitor;
import places.Beach;
import places.beach.Restaurant;
import simulations.BeachSimulation1;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Simulator {

    public static <T> T getInstance(Container c, Class<T> cls){
        try {
            return c.getInstance(cls);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void runSimulation(Container container, int days){
        Beach beach = getInstance(container, Beach.class);
        List<Visitor> outsiders = new ArrayList<Visitor>();
        int totalVisitors = 0;
        int maxNewVisitors = 5;
        int restaurants = 8;
        //Create Restaurants
        List<Restaurant> firstRestaurants = new ArrayList<Restaurant>();
        for (int i=0; i<restaurants; i++) {
            firstRestaurants.add(getInstance(container, Restaurant.class));
        }
        beach.setRestaurants(firstRestaurants);
        for (int day=0; day < days; day++){
            int newVisitors = (int) (Math.random() * maxNewVisitors);
            for (int v=1; v <= newVisitors; v++){
                Visitor visitor = getInstance(container, Visitor.class);
                visitor.setIdentifier(totalVisitors);
                beach.newVisitor(visitor);
                totalVisitors++;
            }
            List<Visitor> currentOutsiders = new ArrayList<Visitor>();
            currentOutsiders.addAll(outsiders);
            for (Visitor visitor : currentOutsiders){
                if (Math.random() < 0.1) {
                    beach.newVisitor(visitor);
                    outsiders.remove(visitor);
                    visitor.comeback();
                }
            }
            List<Visitor> currentVisitors = new ArrayList<Visitor>();
            currentVisitors.addAll(beach.getVisitors());
            for (Visitor visitor : currentVisitors){
                if (Math.random() < 0.5) {
                    // Rest in place

                }
                if (Math.random() < 0.5) {
                    // Swim
                    visitor.swim(beach);
                }
                if (Math.random() < 0.5) {
                    // Eat
                    int res = (int) (Math.random() * beach.getRestaurants().size());
                    Restaurant restaurant = beach.getRestaurants().get(res);
                    visitor.eat(restaurant);
                }
                if (Math.random() < 0.5) {
                    // See turtle
                    //visitor.seeAnimal();
                }
                if (Math.random() < 0.3) {
                    // Leave beach
                    outsiders.add(visitor);
                    visitor.leave();
                    beach.removeVisitor(visitor);
                }
                if (visitor.needsMoney()){
                    visitor.leave();
                }
            }
        }
        List<Visitor> allVisitors = new ArrayList<Visitor>();
        allVisitors.addAll(outsiders);
        allVisitors.addAll(beach.getVisitors());
        for (Visitor visitor : allVisitors){
            visitor.printActions();
        }
    }

    public static void main(String[] args) {
        try {
            Container container = DependencyInjector.run(Simulator.class, "context.xml");
            //Container container = DependencyInjector.run(BeachSimulation1.class);
            runSimulation(container, 10);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
