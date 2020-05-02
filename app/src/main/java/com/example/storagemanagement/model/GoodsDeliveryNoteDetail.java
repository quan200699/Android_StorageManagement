package com.example.storagemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDeliveryNoteDetail {
    private String goodsDeliveryNoteId;
    private String productId;
    private int quantity;
    private double price;
}
