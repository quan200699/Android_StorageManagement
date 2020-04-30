package com.example.storagemanagement.config;

public class StaticVariable {
    public static final String DATABASE_NAME = "storage_management";
    public static final String TABLE_PRODUCT = "products";
    public static final String TABLE_CUSTOMER = "customers";
    public static final String TABLE_SUPPLIER = "suppliers";
    public static final int DATABASE_Version = 1;
    public static final String MESSAGE_CREATE_SUCCESS = "Tạo mới thành công";
    public static final String MESSAGE_DELETE_SUCCESS = "Xóa thành công";
    public static final String MESSAGE_UPDATE_SUCCESS = "Cập nhật thành công";
    public static final String MESSAGE_FAIL = "Có lỗi xảy ra";
    public static final String ID = "id";
    public static final String PRODUCT_ID = "productId";
    public static final String CUSTOMER_ID = "customerId";
    public static final String SUPPLIER_ID = "supplierId";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String DESCRIPTION = "description";
    public static final String GUARANTEE = "guarantee";
    public static final String EMAIL = "email";
    public static final String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " VARCHAR(255) ,"
            + NAME + " VARCHAR(225) ," + DESCRIPTION + " VARCHAR(225) ," + GUARANTEE + " INTEGER);";
    public static final String DROP_TABLE_PRODUCT = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;
    public static final String DROP_TABLE_CUSTOMER = "DROP TABLE IF EXISTS " + TABLE_CUSTOMER;
    public static final String DROP_TABLE_SUPPLIER = "DROP TABLE IF EXISTS " + TABLE_SUPPLIER;
    public static final String SELECT_ALL_ATTRIBUTE = "SELECT * FROM ";
    public static final String WHERE = "WHERE";
    public static final String DELETE_PRODUCT = "Xóa thông tin sản phẩm";
    public static final String ARE_YOU_SURE = "Bạn có chắc muốn xóa thông tin sản phẩm này?";
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String CANCEL = "Cancel";
    public static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE " + TABLE_CUSTOMER +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_ID + " VARCHAR(255) ,"
            + NAME + " VARCHAR(225) ," + ADDRESS + " VARCHAR(225) ," + PHONE_NUMBER + " VARCHAR(225));";
    public static final String CREATE_TABLE_SUPPLIER = "CREATE TABLE " + TABLE_SUPPLIER +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUPPLIER_ID + " VARCHAR(255) ,"
            + NAME + " VARCHAR(225) ," + ADDRESS + " VARCHAR(225) ," + EMAIL + " VARCHAR(225));";
}
