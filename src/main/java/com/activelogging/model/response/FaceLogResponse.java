package com.activelogging.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.activelogging.model.status.FaceLogStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FaceLogResponse {
    @JsonProperty("code")
    public String errorCode;
    @JsonProperty("desc")
    public String errorDesc;
    @JsonProperty("data")
    public Object data;

    public FaceLogResponse error(FaceLogStatus status) {
        errorCode = status.getCode();
        errorDesc = status.getDesc();
        return this;
    }

    public FaceLogResponse data(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "DncResponse{" +
                "code='" + errorCode + '\'' +
                ", desc='" + errorDesc + '\'' +
                ", data=" + data +
                '}';
    }
}
