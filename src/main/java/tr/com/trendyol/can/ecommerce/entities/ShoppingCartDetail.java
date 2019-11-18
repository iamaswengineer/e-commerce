package tr.com.trendyol.can.ecommerce.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@SequenceGenerator(name = "idGenerator", sequenceName = "SEQ_CART_DETAIL", allocationSize = 1)
public class ShoppingCartDetail extends BaseEntity{

    @Column
    private Long quantity;

    @Column
    private Double totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    private ShoppingCart shoppingCart;
}
