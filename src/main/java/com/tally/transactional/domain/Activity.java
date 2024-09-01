package com.tally.transactional.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long id;

    private Long userId;

    @Column(name = "activity_name", nullable = false)
    private String name;

    @Builder
    private Activity(final Long id, final Long userId, final String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public static Activity newOf(final Long userId, final String name) {
        return new Activity(null, userId, name);
    }

}
