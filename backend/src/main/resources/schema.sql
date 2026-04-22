CREATE TABLE IF NOT EXISTS places (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(255) NOT NULL,
    category         ENUM('food', 'hotel', 'experience') NOT NULL,
    region           VARCHAR(100) NOT NULL,
    price            INT          NOT NULL,
    rating           FLOAT        NOT NULL DEFAULT 0,
    duration_minutes INT          NOT NULL DEFAULT 60,
    score            FLOAT        NOT NULL DEFAULT 0,
    lat              DOUBLE       NOT NULL,
    lng              DOUBLE       NOT NULL,
    created_at       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS reviews (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    place_id        BIGINT  NOT NULL,
    content         TEXT    NOT NULL,
    summarized      TEXT,
    sentiment_score FLOAT   NOT NULL DEFAULT 0,
    created_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_review_place FOREIGN KEY (place_id) REFERENCES places (id) ON DELETE CASCADE
);

-- 인덱스
CREATE INDEX idx_places_region_category ON places (region, category);
CREATE INDEX idx_reviews_place_id       ON reviews (place_id);
