package christmas.model.presentation;

import christmas.model.order.Orders;

public interface Presentation {

    String getPresentationName();

    int getBenefit();

    boolean isPresentation(Orders orders);
}
