package tr.com.trendyol.can.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCOUNT_TYPE")
@SequenceGenerator(name = "idGenerator", sequenceName = "SEQ_DISCOUNT", allocationSize = 1)
@Getter
@Setter
public abstract class Discount extends BaseEntity{

    @Column
    private Double amount;

    @Column(name = "DISCOUNT_STRATEGY")
    protected Long discountStrategy;
}
