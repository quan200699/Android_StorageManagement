package com.example.storagemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDeliveryNote {
    private int id;
    private String goodsDeliveryNoteId;
    private String date;
    private String customerId;
    private String wareHouseId;
    private String employeeId;
    private String notice;

    public GoodsDeliveryNote(String goodsDeliveryNoteId, String date, String customerId, String wareHouseId, String employeeId, String notice) {
        this.goodsDeliveryNoteId = goodsDeliveryNoteId;
        this.date = date;
        this.customerId = customerId;
        this.wareHouseId = wareHouseId;
        this.employeeId = employeeId;
        this.notice = notice;
    }
}
