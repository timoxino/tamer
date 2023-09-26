package com.timoxino.interview.tamer.service;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;

@Service
public class GcsService implements StorageService {

    private final static Logger LOGGER = LoggerFactory.getLogger(GcsService.class);

    @Value("gs://interview_cv")
    private Resource cvBucket;

    @Value("gs://interview_cv_evaluation")
    private Resource evaluationBucket;

    @Value("gs://interview_cv_skills")
    private Resource skillsBucket;

    @Autowired
    Storage storage;

    public String readCvFile(String fileName) throws IOException {
        LOGGER.info("Reading the file {} from the bucket gs://interview_cv", fileName);
        // Storage storage = StorageOptions.newBuilder().build().getService();
        Blob blob = null;
        try {

            BlobId blobId = BlobId.of("gs://interview_cv", fileName);
            LOGGER.info("BlobId created");
            blob = storage.get(blobId);
            LOGGER.info("Blob retrieved");

            if (blob == null) {
                throw new NullPointerException("Blob object is not initialized.");
            }
        } catch (Exception e) {
            LOGGER.error("Error while reading the file. Exception: {}. Stack trace: ", e.getClass().toString(), e.getStackTrace());
        }

        byte[] fileContent = blob.getContent();
        LOGGER.info("Blob content retrieved");
        return new String(fileContent);

        /*
         * return StreamUtils.copyToString(
         * cvBucket.getInputStream(),
         * Charset.defaultCharset());
         */
    }

    @Override
    public void writeEvaluationFile(String fileName, String fileContent) throws IOException {
        writeFile(fileName, fileContent, evaluationBucket);
    }

    @Override
    public void writeSkillsFile(String fileName, String fileContent) throws IOException {
        writeFile(fileName, fileContent, skillsBucket);
    }

    private void writeFile(String fileName, String fileContent, Resource bucket) throws IOException {
        Resource file = bucket.createRelative(fileName);
        try (OutputStream os = ((WritableResource) file).getOutputStream()) {
            os.write(fileContent.getBytes());
        }
    }
}
