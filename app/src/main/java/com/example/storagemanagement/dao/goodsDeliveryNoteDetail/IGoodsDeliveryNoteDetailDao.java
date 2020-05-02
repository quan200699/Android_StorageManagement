package com.example.storagemanagement.dao.goodsDeliveryNoteDetail;

import com.example.storagemanagement.dao.IGeneralDao;
import com.example.storagemanagement.model.GoodsDeliveryNoteDetail;

import java.util.List;

public interface IGoodsDeliveryNoteDetailDao extends IGeneralDao<GoodsDeliveryNoteDetail> {
    List<GoodsDeliveryNoteDetail> findAllByGoodsDeliveryNoteId(String goodsDeliveryNoteId);
}
