CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    role VARCHAR(20) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    addr VARCHAR(150) NOT NULL
);

INSERT INTO users (role, username, password, addr)
VALUES ('user', 'john_doe', 'password123', '123 Main St, Anytown USA');

-- DROP TABLE users;

CREATE TABLE objects (
    id SERIAL PRIMARY KEY,
    ownerID INTEGER REFERENCES users(id),
    img_url VARCHAR(500) NOT NULL,
    title VARCHAR(50) NOT NULL,
    date_dispo VARCHAR(150) NOT NULL,
    prix VARCHAR(20) NOT NULL
);

CREATE TABLE command (
    id SERIAL PRIMARY KEY,
    objectID INTEGER REFERENCES objects(id),
    commanderID INTEGER REFERENCES users(id),
    ownerID INTEGER REFERENCES users(id)
);

CREATE TABLE panier (
    id SERIAL PRIMARY KEY,
    objectID INTEGER REFERENCES objects(id),
    userID INTEGER REFERENCES users(id)
);

CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    objectID INTEGER REFERENCES objects(id),
    userID INTEGER REFERENCES users(id)
);