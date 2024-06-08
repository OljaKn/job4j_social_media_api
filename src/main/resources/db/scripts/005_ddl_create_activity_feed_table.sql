create table activity_feed (
id serial primary key,
"user" int not null references users(id),
post int not null references posts(id)
)