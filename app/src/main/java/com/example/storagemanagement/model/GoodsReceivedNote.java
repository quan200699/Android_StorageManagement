package com.example.storagemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsReceivedNote {
    private int id;
    private String goodsReceivedNoteId;
    private String date;
    private String supplierId;
    private String warehouseId;
    private String employeeId;
    private String notice;

    public GoodsReceivedNote(String goodsReceivedNoteId, String date, String supplierId, String warehouseId, String employeeId, String notice) {
        this.goodsReceivedNoteId = goodsReceivedNoteId;
        this.date = date;
        this.supplierId = supplierId;
        this.warehouseId = warehouseId;
        this.employeeId = employeeId;
        this.notice = notice;
    }

    public GoodsReceivedNote(String goodsReceivedNoteId, String date, String warehouseId, String notice) {
        this.goodsReceivedNoteId = goodsReceivedNoteId;
        this.date = date;
        this.warehouseId = warehouseId;
        this.notice = notice;
    }

    public GoodsReceivedNote(String goodsReceivedNoteId, String date, String notice) {
        this.goodsReceivedNoteId = goodsReceivedNoteId;
        this.date = date;
        this.notice = notice;
    }

    public GoodsReceivedNote(int id, String goodsReceivedNoteId, String date, String warehouseId, String notice) {
        this.id = id;
        this.goodsReceivedNoteId = goodsReceivedNoteId;
        this.date = date;
        this.warehouseId = warehouseId;
        this.notice = notice;
    }
}
