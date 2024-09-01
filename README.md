### 문제 상황
내부 트랜잭션에서 예외가 발생하면, 외부 트랜잭션에서 try-catch로 처리해도 전체 트랜잭션이 롤백됨.

### 문제 원인

- Spring의 트랜잭션 관리 메커니즘
- isGlobalRollbackOnParticipationFailure()가 기본적으로 true로 설정됨
- RuntimeException이 발생하면 롤백 마크가 설정됨


### 해결 방법

- Checked Exception 사용
- @Transactional(noRollbackFor = RuntimeException.class) 사용
- @Transactional(propagation = Propagation.REQUIRES_NEW) 사용

---

```mermaid
sequenceDiagram
    participant Client
    participant MigrationController
    participant MigrationService
    participant MigrationWriter
    participant TransactionManager
    participant ActivityRepository
    participant Database

    Client->>MigrationController: POST /api/migrate
    activate MigrationController
    MigrationController->>MigrationService: migrate()
    activate MigrationService
    MigrationService->>MigrationService: Create List<Activity>
    MigrationService->>MigrationWriter: saveAll(activities)
    activate MigrationWriter
    MigrationWriter->>TransactionManager: Begin Transaction
    activate TransactionManager
    TransactionManager-->>MigrationWriter: Transaction Started
    MigrationWriter->>ActivityRepository: saveAll(activities)
    activate ActivityRepository
    ActivityRepository->>Database: Save Activities
    alt Successful save
        Database-->>ActivityRepository: Success
        ActivityRepository-->>MigrationWriter: Return
    else Exception occurs
        Database-->>ActivityRepository: Throw Exception
        ActivityRepository-->>MigrationWriter: Propagate Exception
        MigrationWriter->>MigrationWriter: Log Error
    end
    deactivate ActivityRepository
    MigrationWriter->>TransactionManager: Commit Transaction
    TransactionManager->>TransactionManager: Check noRollbackFor
    TransactionManager-->>MigrationWriter: Transaction Committed
    deactivate TransactionManager
    MigrationWriter-->>MigrationService: Return (No Exception thrown)
    deactivate MigrationWriter
    MigrationService-->>MigrationController: Return
    deactivate MigrationService
    MigrationController-->>Client: 200 OK
    deactivate MigrationController
```