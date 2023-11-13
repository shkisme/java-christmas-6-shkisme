package christmas.model.price;

public class Price {
    private final int beforePrice;
    private final int benefitsPrice;
    private final int presentsPrice;

    public Price(int beforePrice, int benefitsPrice, int presentsPrice) {
        this.beforePrice = beforePrice;
        this.benefitsPrice = benefitsPrice;
        this.presentsPrice = presentsPrice;
    }

    public int getTotalBenefits() {
        return benefitsPrice + presentsPrice;
    }

    public int getAfterPrice() {
        return beforePrice - benefitsPrice;
    }

    public int getBeforePrice() {
        return beforePrice;
    }
}
