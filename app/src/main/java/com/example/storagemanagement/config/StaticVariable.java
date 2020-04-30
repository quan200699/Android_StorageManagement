package com.example.storagemanagement.config;

public class StaticVariable {
    public static final String DATABASE_NAME = "storage_management";
    public static final String TABLE_PRODUCT = "products";
    public static final int DATABASE_Version = 1;
    public static final String MESSAGE_CREATE_SUCCESS = "Tạo mới thành công";
    public static final String MESSAGE_DELETE_SUCCESS = "Xóa thành công";
    public static final String MESSAGE_UPDATE_SUCCESS = "Cập nhật thành công";
    public static final String MESSAGE_FAIL = "Có lỗi xảy ra";
    public static final String ID = "id";
    public static final String PRODUCT_ID = "productId";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String GUARANTEE = "guarantee";
    public static final String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " VARCHAR(255) ,"
            + NAME + " VARCHAR(225) ," + DESCRIPTION + " VARCHAR(225) ," + GUARANTEE + " INTEGER);";
    public static final String DROP_TABLE_PRODUCT = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;
    public static final String SELECT_ALL_PRODUCT = "SELECT * FROM " + TABLE_PRODUCT;
    public static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM " + TABLE_PRODUCT + " WHERE ID=";
}
