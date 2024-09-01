package com.tally.transactional.controller;

import com.tally.transactional.service.MigrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MigrationController {

    private final MigrationService migrationService;

    @PostMapping("/api/migrate")
    public void migrate() {
        migrationService.migrate();
    }


}
