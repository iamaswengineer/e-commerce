package tr.com.trendyol.can.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "idGenerator", sequenceName = "SEQ_CATEGORY", allocationSize = 1)
public class Category extends BaseEntity{
    @Column
    private String title;

}
