package people;

import com.darkj24.ioc.annotations.Autowired;
import com.darkj24.ioc.annotations.Bean;
import com.darkj24.ioc.annotations.Lazy;
import com.darkj24.ioc.annotations.Prototype;
import com.darkj24.ioc.enums.AutowiringMode;

@Bean(autowire = AutowiringMode.BY_NAME)
@Prototype
public class Owner {

    private Double money;
    private String name;
    private String type;

    public Owner(String name, String type) {
        this.money = 0.0;
        this.name = name;
        this.type = type;
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
        this.money -= money;
    }

    public void print(){
        System.out.println(this.name + " dueño de " + type + " con $" + money +" de ingresos");
    }
}
