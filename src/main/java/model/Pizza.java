package model;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by alexandrsemenov on 27.02.17.
 */
public class Pizza {
    private long id;
    private String name;
    private BigDecimal price;
    private Collection<Component> components;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Collection<Component> getComponents() {
        return components;
    }

    public void setComponents(Collection<Component> components) {
        this.components = components;
    }
}
