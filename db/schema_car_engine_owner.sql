CREATE TABLE if not exists engine (
   id SERIAL PRIMARY KEY,
   model TEXT,
   serialnum int
);

CREATE TABLE if not exists car (
   id SERIAL PRIMARY KEY,
   model TEXT,
   engine_id int not null unique references engine(id)
);

CREATE TABLE if not exists driver (
  id SERIAL PRIMARY KEY,
  name TEXT
);

CREATE TABLE if not exists history_owner (
   driver_id int not null references driver(id),
   car_id int not null references car(id),
   PRIMARY KEY (driver_id, car_id)
);