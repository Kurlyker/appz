package javaproject.demo.stubs;


import javaproject.demo.entities.Client;
import javaproject.demo.entities.Order;
import javaproject.demo.entities.Car;

import java.util.Date;

public final class OrderStub {
    public static final Long ID = 1L;
    public static final String DESCRIPTION = "description";
    public static final Client CLIENT = ClientStub.getRandomClient();
    public static final Car PRODUCT = ProductStub.getRandomProduct();
    public static final Date DATE = new Date();
    public static Order getRandomOrder() {
        return Order.builder()
                .id(ID)
                .description(DESCRIPTION)
                .createdDate(DATE)
                .client(CLIENT)
                .product(PRODUCT)
                .build();
    }
}
