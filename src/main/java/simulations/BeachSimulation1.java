package simulations;

import com.darkj24.ioc.annotations.Bean;
import com.darkj24.ioc.annotations.Provider;
import com.darkj24.ioc.annotations.Qualifier;
import people.Owner;

@Provider
public class BeachSimulation1 {

    public BeachSimulation1() {
    }

    @Qualifier("beachOwner")
    @Bean
    public Owner beachOwner(){
        return new Owner("Israel Chaves", "Playa");
    }

    @Qualifier("restaurantsOwner")
    @Bean
    public Owner restaurantsOwner(){
        return new Owner("Jose Quesada", "Restaurante");
    }
}
