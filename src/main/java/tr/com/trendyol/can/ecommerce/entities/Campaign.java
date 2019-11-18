package tr.com.trendyol.can.ecommerce.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CAMPAIGN")
@Getter
@Setter
@NoArgsConstructor
public class Campaign extends Discount{

    public Campaign(Double amount, Long discountStrategy) {
        super(amount, discountStrategy);
    }
}
