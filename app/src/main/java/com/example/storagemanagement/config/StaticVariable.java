package com.example.storagemanagement.config;

public class StaticVariable {
    public static final String DATABASE_NAME = "storage_management";
    public static final String TABLE_PRODUCT = "products";
    public static final String TABLE_CUSTOMER = "customers";
    public static final String TABLE_SUPPLIER = "suppliers";
    public static final String TABLE_EMPLOYEE = "employees";
    public static final String TABLE_WAREHOUSE = "warehouses";
    public static final String TABLE_GOODS_DELIVERY_NOTE = "goods_delivery_note";
    public static final String TABLE_GOODS_DELIVERY_NOTE_DETAIL = "goods_delivery_note_detail";
    public static final String TABLE_GOODS_RECEIVED_NOTE = "goods_received_note";
    public static final String TABLE_GOODS_RECEIVED_NOTE_DETAIL = "goods_received_note_detail";
    public static final int DATABASE_Version = 1;
    public static final String MESSAGE_CREATE_SUCCESS = "Tạo mới thành công";
    public static final String MESSAGE_DELETE_SUCCESS = "Xóa thành công";
    public static final String MESSAGE_UPDATE_SUCCESS = "Cập nhật thành công";
    public static final String MESSAGE_FAIL = "Có lỗi xảy ra";
    public static final String ID = "id";
    public static final String PRODUCT_ID = "productId";
    public static final String CUSTOMER_ID = "customerId";
    public static final String SUPPLIER_ID = "supplierId";
    public static final String EMPLOYEE_ID = "employeeId";
    public static final String WAREHOUSE_ID = "wareHouseId";
    public static final String GOODS_DELIVERY_NOTE_ID = "goodsDeliveryNoteId";
    public static final String GOODS_RECEIVED_NOTE_ID = "goodsReceivedNoteId";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String DESCRIPTION = "description";
    public static final String GUARANTEE = "guarantee";
    public static final String EMAIL = "email";
    public static final String BIRTHDAY = "birthday";
    public static final String SEX = "sex";
    public static final String DATE = "date";
    public static final String NOTICE = "notice";
    public static final String QUANTITY = "quantity";
    public static final String PRICE = "price";
    public static final String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " VARCHAR(255) ,"
            + NAME + " VARCHAR(225) ," + DESCRIPTION + " VARCHAR(225) ," + GUARANTEE + " INTEGER);";
    public static final String DROP_TABLE_PRODUCT = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;
    public static final String DROP_TABLE_CUSTOMER = "DROP TABLE IF EXISTS " + TABLE_CUSTOMER;
    public static final String DROP_TABLE_SUPPLIER = "DROP TABLE IF EXISTS " + TABLE_SUPPLIER;
    public static final String DROP_TABLE_EMPLOYEE = "DROP TABLE IF EXISTS " + TABLE_EMPLOYEE;
    public static final String DROP_TABLE_WAREHOUSE = "DROP TABLE IF EXISTS " + TABLE_WAREHOUSE;
    public static final String DROP_TABLE_GOODS_DELIVERY_NOTE = "DROP TABLE IF EXISTS " + TABLE_GOODS_DELIVERY_NOTE;
    public static final String DROP_TABLE_GOODS_DELIVERY_NOTE_DETAIL = "DROP TABLE IF EXISTS " + TABLE_GOODS_DELIVERY_NOTE_DETAIL;
    public static final String DROP_TABLE_GOODS_RECEIVED_NOTE = "DROP TABLE IF EXISTS " + TABLE_GOODS_RECEIVED_NOTE;
    public static final String DROP_TABLE_GOODS_RECEIVED_NOTE_DETAIL = "DROP TABLE IF EXISTS " + TABLE_GOODS_RECEIVED_NOTE_DETAIL;
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
    public static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " + TABLE_EMPLOYEE +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMPLOYEE_ID + " VARCHAR(255) ,"
            + NAME + " VARCHAR(225) ," + BIRTHDAY + " VARCHAR(225) ," + SEX + " VARCHAR(225) ," + ADDRESS + " VARCHAR(225));";
    public static final String CREATE_TABLE_WAREHOUSE = "CREATE TABLE " + TABLE_WAREHOUSE +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + WAREHOUSE_ID + " VARCHAR(255) ,"
            + NAME + " VARCHAR(225) ," + ADDRESS + " VARCHAR(225));";
    public static final String CREATE_TABLE_GOODS_DELIVERY_NOTE = "CREATE TABLE " + TABLE_GOODS_DELIVERY_NOTE +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GOODS_DELIVERY_NOTE_ID + " VARCHAR(255) ,"
            + DATE + " VARCHAR(225) ," + CUSTOMER_ID + " VARCHAR(225) ," + WAREHOUSE_ID + " VARCHAR(225) ,"
            + EMPLOYEE_ID + " VARCHAR(225) ," + NOTICE + " VARCHAR(225));";
    public static final String CREATE_TABLE_GOODS_DELIVERY_NOTE_DETAIL = "CREATE TABLE " + TABLE_GOODS_DELIVERY_NOTE_DETAIL +
            " (" + GOODS_DELIVERY_NOTE_ID + " VARCHAR(255) ,"
            + PRODUCT_ID + " VARCHAR(225) ," + QUANTITY + " INTEGER ," + PRICE + " REAL);";
    public static final String CREATE_TABLE_GOODS_RECEIVED_NOTE = "CREATE TABLE " + TABLE_GOODS_RECEIVED_NOTE +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GOODS_RECEIVED_NOTE_ID + " VARCHAR(255) ,"
            + DATE + " VARCHAR(225) ," + SUPPLIER_ID + " VARCHAR(225) ," + WAREHOUSE_ID + " VARCHAR(225) ,"
            + EMPLOYEE_ID + " VARCHAR(225) ," + NOTICE + " VARCHAR(225));";
    public static final String CREATE_TABLE_GOODS_RECEIVED_NOTE_DETAIL = "CREATE TABLE " + TABLE_GOODS_DELIVERY_NOTE_DETAIL +
            " (" + GOODS_RECEIVED_NOTE_ID + " VARCHAR(255) ,"
            + PRODUCT_ID + " VARCHAR(225) ," + QUANTITY + " INTEGER ," + PRICE + " REAL);";
}
