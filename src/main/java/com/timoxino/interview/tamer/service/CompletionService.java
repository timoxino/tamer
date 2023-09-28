package com.timoxino.interview.tamer.service;

import java.util.List;

public interface CompletionService {
    Integer evaluateSeniorityLevel(String cv, String role);

    List<String> detectSkills(String cv, String role);
}
