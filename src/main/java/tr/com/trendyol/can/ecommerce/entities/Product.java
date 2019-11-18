package tr.com.trendyol.can.ecommerce.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idGenerator", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
public class Product extends BaseEntity{

    @Column
    private String title;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;
}
