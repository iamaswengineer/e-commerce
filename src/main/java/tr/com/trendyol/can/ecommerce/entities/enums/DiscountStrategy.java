package tr.com.trendyol.can.ecommerce.entities.enums;

public enum DiscountStrategy {
    AMOUNT(1),
    RATE(2);

    private long value;

    DiscountStrategy(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public String getLabel() {
        return name();
    }

}
