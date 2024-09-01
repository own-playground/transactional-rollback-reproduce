package com.tally.transactional.service;

import com.tally.transactional.adapter.MigrationWriter;
import com.tally.transactional.domain.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MigrationService {

    private final MigrationWriter writer;

    public void migrate() {
        final List<Activity> activities = List.of(
                Activity.newOf(1L, "test1"),
                Activity.newOf(3L, "test2test2test2test2test2test2test2test2test2test2test2test2"));
        writer.saveAll(activities);
    }

}
