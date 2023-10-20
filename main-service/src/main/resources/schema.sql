CREATE TABLE IF NOT EXISTS users
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name  VARCHAR(250)                            NOT NULL,
    email VARCHAR(254)                            NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uq_user_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS categories
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(50)                             NOt NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id),
    CONSTRAINT uq_category_name (name)
);

CREATE TABLE IF NOT EXISTS locations
(
    id  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    lat FLOAT                                   NOT NULL,
    lon FLOAT                                   NOT NULL,
    CONSTRAINT pk_locations PRIMARY KEY (id),
    CONSTRAINT uq_location UNIQUE (lat, lon)
);

CREATE TABLE IF NOT EXISTS events
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY                                   NOT NULL,
    annotation         VARCHAR(2000)                                                             NOT NULL,
    category_id        BIGINT REFERENCES categories (id) ON DELETE RESTRICT,
    confirmed_requests INT                                                                       NOT NULL,
    created_on         TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP                     NOT NULL,
    description        VARCHAR(7000)                                                             NOT NULL,
    event_date         TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP + INTERVAL '2' HOUR NOT NULL,
    initiator_id       BIGINT REFERENCES users (id) ON DELETE RESTRICT,
    location_id        BIGINT REFERENCES locations (id) ON DELETE RESTRICT,
    paid               BOOLEAN,
    participant_limit  INT                                                                       NOT NULL,
    published_on       TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP                     NOT NULL,
    request_moderation BOOLEAN,
    state              VARCHAR(16)                 DEFAULT 'PENDING',
    title              VARCHAR(120)                                                              NOT NULL,
    CONSTRAINT pk_events PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS requests
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    created TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    event_id BIGINT REFERENCES events (id) ON DELETE RESTRICT,
    requester_id BIGINT REFERENCES users (id) ON DELETE RESTRICT,
    status VARCHAR(16) DEFAULT 'PENDING',
    CONSTRAINT uq_request UNIQUE (event_id, requester_id)
);

CREATE TABLE IF NOT EXISTS compilations (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    title VARCHAR(50) NOT NULL,
    pinned BOOLEAN NOT NULL,
    CONSTRAINT pk_compilations PRIMARY KEY (id),
    CONSTRAINT uq_compilations_title UNIQUE (title)
);

CREATE TABLE IF NOT EXISTS compilations_events (
    event_id BIGINT REFERENCES events (id) ON DELETE RESTRICT,
    compilation_id BIGINT REFERENCES compilations (id) ON DELETE RESTRICT,
    CONSTRAINT pk_compilations_event PRIMARY KEY (event_id, compilation_id)
);
