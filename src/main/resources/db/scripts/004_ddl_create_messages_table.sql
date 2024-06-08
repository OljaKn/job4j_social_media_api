create table messages (
    id SERIAL PRIMARY KEY,
    "text" TEXT,
    "data" timestamp,
    sender int not null references users(id),
    receiver int not null references users(id),
    status boolean
);