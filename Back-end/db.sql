CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    role VARCHAR(20) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    addr VARCHAR(150) NOT NULL
);

CREATE TABLE objects (
    id SERIAL PRIMARY KEY,
    ownerID INTEGER REFERENCES users(id),
    img_url VARCHAR(500) NOT NULL,
    title VARCHAR(50) NOT NULL,
    date_dispo_start VARCHAR(100),
    date_dispo_end VARCHAR(100),
    prix VARCHAR(20) NOT NULL,
    res_status VARCHAR(10) NOT NULL,
    res_by INTEGER REFERENCES users(id)
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
    userID INTEGER REFERENCES users(id),
    comment VARCHAR(500) NOT NULL
);