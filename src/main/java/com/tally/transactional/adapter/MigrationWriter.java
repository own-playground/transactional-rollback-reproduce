package com.tally.transactional.adapter;

import com.tally.transactional.domain.Activity;
import com.tally.transactional.domain.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationWriter {

    private final ActivityRepository activityRepository;

    @Transactional(noRollbackFor = Exception.class)
    public void saveAll(final List<Activity> activities) {
        try {
            activityRepository.saveAll(activities);
        } catch (Exception e) {
            log.error("에러 발생", e);
        }
    }
}
