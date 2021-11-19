package com.activelogging.services;

import com.activelogging.configurations.CoreActiveLoggingConfig;
import com.activelogging.model.request.UploadFaceLogRequest;
import com.activelogging.model.response.FaceLogResponse;
import com.activelogging.model.status.FaceLogStatus;
import com.activelogging.worker.DatabaseWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class LoggingService {

    @Autowired
    private CoreActiveLoggingConfig config;
    @Autowired
    private DatabaseWorker databaseWorker;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public Mono<FaceLogResponse> handleReport(byte[] file, UploadFaceLogRequest request) {
        Path path = buildPath(request.msisdn, request.mediaType);
        if (path != null) {
            saveFaceLog(file, request.mediaType, path);
            saveReport(request, path);
            return Mono.just(new FaceLogResponse().error(FaceLogStatus.OK));
        }
        return Mono.just(new FaceLogResponse().error(FaceLogStatus.EXCEPTION));
    }

    private void saveFaceLog(byte[] file, String mediaType, Path path) {
        executor.submit(() -> {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(file);
                BufferedImage bi = ImageIO.read(bis);
                ImageIO.write(bi, mediaType, new File(path.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void saveReport(UploadFaceLogRequest request, Path path) {
      //todo save record to database
    }

    private Path buildPath(String msisdn, String mediaType) {
        File reportStorage = new File(config.getFaceLoggingStorage());
        if (reportStorage.exists() || reportStorage.mkdirs()) {
            File dateFolder = new File(config.getFaceLoggingStorage() + "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            if (dateFolder.exists() || dateFolder.mkdirs()) {
                return Paths.get(
                    dateFolder.getAbsolutePath(),
                    msisdn + "_" + new SimpleDateFormat("HH-mm-ss").format(new Date()) + "." + mediaType
                );
            }
        }
        return null;
    }
}

