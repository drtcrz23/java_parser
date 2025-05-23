CREATE TABLE films (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    release_year INT NOT NULL,
    url VARCHAR(255) NOT NULL UNIQUE,
    rating DECIMAL(3,1),
    country_id BIGINT,
    FOREIGN KEY (country_id) REFERENCES countries(id)
);

CREATE TABLE film_genres (
    film_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    PRIMARY KEY (film_id, genre_id),
    FOREIGN KEY (film_id) REFERENCES films(id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE
);

CREATE TABLE film_directors (
    film_id BIGINT NOT NULL,
    director_id BIGINT NOT NULL,
    PRIMARY KEY (film_id, director_id),
    FOREIGN KEY (film_id) REFERENCES films(id) ON DELETE CASCADE,
    FOREIGN KEY (director_id) REFERENCES directors(id) ON DELETE CASCADE
);