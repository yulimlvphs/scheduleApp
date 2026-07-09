https://documenter.getpostman.com/view/54709111/2sBY4JxP96

erDiagram
USER ||--o{ SCHEDULE : writes
USER ||--o{ COMMENT : writes
SCHEDULE ||--o{ COMMENT : has

    USER {
        BIGINT id PK
        VARCHAR username
        VARCHAR email
        VARCHAR password
        DATETIME created_at
        DATETIME modified_at
    }

    SCHEDULE {
        BIGINT id PK
        VARCHAR title
        TEXT content
        BIGINT user_id FK
        DATETIME created_at
        DATETIME modified_at
    }

    COMMENT {
        BIGINT id PK
        TEXT content
        BIGINT user_id FK
        BIGINT schedule_id FK
        DATETIME created_at
        DATETIME modified_at
    }