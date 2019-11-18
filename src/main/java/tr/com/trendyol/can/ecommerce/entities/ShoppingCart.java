package tr.com.trendyol.can.ecommerce.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@SequenceGenerator(name = "idGenerator", sequenceName = "SEQ_CART", allocationSize = 1)
@Getter
@Setter
public class ShoppingCart extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user;

    @Column
    private Double totalCartPrice;

    @Column
    private Double couponDiscountAmount;

    @Column
    private Double campaignDiscountAmount;

    @Column
    private Double cartPriceAfterDiscount;

    @OneToMany( mappedBy = "shoppingCart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ShoppingCartDetail> shoppingCartDetailList = new ArrayList<>();
}
