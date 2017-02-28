package model;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by alexandrsemenov on 27.02.17.
 */
public interface ComponentDAO  {
    Component read(long id);
    Component create(String name, long price);
}
