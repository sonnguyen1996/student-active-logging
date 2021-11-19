package com.activelogging.model.status;

public enum FaceLogStatus {

    OK("200", "Thành công"),
    EXCEPTION("500", "Lỗi hệ thống"),
    INVALID_PARAM("-1", "Dữ liệu tham số truyền lên không đúng");

    private final String code, desc;

    FaceLogStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
