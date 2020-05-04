package com.example.storagemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsReceivedNoteDetail {
    private int id;
    private String goodsReceivedNoteId;
    private String productId;
    private int quantity;
    private double price;

    public GoodsReceivedNoteDetail(String goodsReceivedNoteId, String productId, int quantity, double price) {
        this.goodsReceivedNoteId = goodsReceivedNoteId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public GoodsReceivedNoteDetail(String goodsReceivedNoteId, int quantity, double price) {
        this.goodsReceivedNoteId = goodsReceivedNoteId;
        this.quantity = quantity;
        this.price = price;
    }
}
