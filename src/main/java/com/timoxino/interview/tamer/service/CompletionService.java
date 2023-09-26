package com.timoxino.interview.tamer.service;

import java.util.List;

public interface CompletionService {
    String evaluateSeniorityLevel(String cv);

    List<String> detectSkills(String cv);
}
