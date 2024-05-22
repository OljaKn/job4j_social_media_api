CREATE TABLE posts (
   id SERIAL PRIMARY KEY,
   title TEXT,
   description TEXT,
   image varchar,
   created TIMESTAMP,
   user_id int references users(id)
);