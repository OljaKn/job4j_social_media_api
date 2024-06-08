create table subscribers (
    id SERIAL PRIMARY KEY,
    subscriber int not null references users(id),
    subscribed int not null references users(id),
    status boolean
);