package com.activelogging.controller;

import com.google.gson.Gson;
import com.activelogging.model.request.UploadFaceLogRequest;
import com.activelogging.model.response.FaceLogResponse;
import com.activelogging.model.status.FaceLogStatus;
import com.activelogging.services.LoggingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("logging")
public class LoggingController {

    @Autowired
    private LoggingService service;

    @PostMapping(value = "/uploadFaceLog",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<FaceLogResponse> uploadFaceLog(
            @RequestPart("file") byte[] file,
            @RequestPart("userData") String request
    ) {
        Logger.getLogger("TrackingController").info("/report: file=" + file.length + ", request=" + request);
        System.out.println(request);
        return handleReport(file, new Gson().fromJson(request, UploadFaceLogRequest.class)).doOnSuccess(
                faceLogResponse -> Logger.getLogger("TrackingController").info("/report: response=" + faceLogResponse)
        );
    }

    private Mono<FaceLogResponse> handleReport(byte[] file, UploadFaceLogRequest request) {
        try {
            if (file != null && file.length > 0 && request.isValid()) {
                return service.handleReport(file, request);
            }
            return Mono.just(new FaceLogResponse().error(FaceLogStatus.INVALID_PARAM));
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(new FaceLogResponse().error(FaceLogStatus.EXCEPTION));
        }
    }
}
