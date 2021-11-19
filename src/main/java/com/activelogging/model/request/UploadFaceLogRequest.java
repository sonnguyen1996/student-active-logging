package com.activelogging.model.request;

import com.google.gson.annotations.SerializedName;
import org.springframework.util.StringUtils;

public class UploadFaceLogRequest {
    @SerializedName("msisdn")
    public String msisdn;

    @SerializedName("rooom_id")
    public String roomID;

    @SerializedName("media_type")
    public String mediaType;

    public boolean isValid() {
        return StringUtils.hasLength(msisdn)
                && StringUtils.hasLength(mediaType);
    }

    @Override
    public String toString() {
        return "ReportRequest{" +
                "msisdn='" + msisdn + '\'' +
                ", roomID='" + roomID + '\'' +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }
}
