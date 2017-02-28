package model;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by alexandrsemenov on 27.02.17.
 */
public interface PizzaDAO {
    Pizza read(long id);
    Pizza create(String name, BigDecimal price, Collection<Component> componentses);
}
