CREATE TABLE IF NOT EXISTS Todo (
    id INT NOT NULL,
    title varchar(256) NOT NULL,
    content TEXT NOT NULL,
    completion INT NOT NULL,
    done BOOLEAN,
    version INT,
    PRIMARY KEY (id)
);