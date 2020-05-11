package com.example.storagemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDeliveryNoteDetail {
    private int id;
    private String goodsDeliveryNoteId;
    private String productId;
    private int quantity;
    private double price;

    public GoodsDeliveryNoteDetail(String goodsDeliveryNoteId, String productId, int quantity, double price) {
        this.goodsDeliveryNoteId = goodsDeliveryNoteId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public GoodsDeliveryNoteDetail(String goodsDeliveryNoteId, int quantity, double price) {
        this.goodsDeliveryNoteId = goodsDeliveryNoteId;
        this.quantity = quantity;
        this.price = price;
    }
}
