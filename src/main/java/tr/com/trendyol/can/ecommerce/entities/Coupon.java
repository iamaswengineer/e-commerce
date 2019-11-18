package tr.com.trendyol.can.ecommerce.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("COUPON")
@Getter
@Setter
@NoArgsConstructor
public class Coupon extends Discount{

}
