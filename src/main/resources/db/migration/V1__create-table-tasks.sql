CREATE TABLE tasks(
    id CHAR(36) NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(5000),
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    PRIMARY KEY(id)
);
