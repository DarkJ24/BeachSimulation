package places.beach;

import com.darkj24.ioc.annotations.Autowired;
import com.darkj24.ioc.annotations.Bean;
import com.darkj24.ioc.annotations.Init;
import com.darkj24.ioc.annotations.Prototype;
import com.darkj24.ioc.enums.AutowiringMode;

@Bean(autowire = AutowiringMode.NO)
@Prototype
public class RestingPlace {

    private Double costPerDay;

    @Autowired
    public RestingPlace() {
    }

    @Init
    public void init() {
        this.costPerDay = Math.random() * 100;
    }

    public Double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(Double costPerDay) {
        this.costPerDay = costPerDay;
    }
}
