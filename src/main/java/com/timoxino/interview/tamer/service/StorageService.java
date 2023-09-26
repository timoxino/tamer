package com.timoxino.interview.tamer.service;

import java.io.IOException;

public interface StorageService {
    String readCvFile(String fileName) throws IOException;

    void writeEvaluationFile(String fileName, String fileContent) throws IOException;

    void writeSkillsFile(String fileName, String fileContent) throws IOException;
}
