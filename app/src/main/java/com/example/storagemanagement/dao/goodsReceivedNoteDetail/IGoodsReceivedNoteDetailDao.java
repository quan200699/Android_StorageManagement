package com.example.storagemanagement.dao.goodsReceivedNoteDetail;

import com.example.storagemanagement.dao.IGeneralDao;
import com.example.storagemanagement.model.GoodsReceivedNoteDetail;

import java.util.List;

public interface IGoodsReceivedNoteDetailDao extends IGeneralDao<GoodsReceivedNoteDetail> {
    List<GoodsReceivedNoteDetail> findAllByGoodsReceivedNoteId(String goodsReceivedNoteId);

}
