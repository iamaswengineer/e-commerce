package tr.com.trendyol.can.ecommerce.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@SequenceGenerator(name = "idGenerator", sequenceName = "SEQ_USER", allocationSize = 1)
@Setter
@Getter
public class User extends BaseEntity{

    @Column
    private String name;

    @OneToOne(mappedBy = "user")
    private ShoppingCart shoppingCart;

}
