CREATE TABLE user_events_entity (
    id          BIGSERIAL PRIMARY KEY,
    user_id     VARCHAR(255) NOT NULL,
    event_type  VARCHAR(255) NOT NULL,
    event_data  TIMESTAMP    NOT NULL,
    details VARCHAR(255)
);