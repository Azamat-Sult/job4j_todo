CREATE TABLE if not exists item (
   id SERIAL PRIMARY KEY,
   task TEXT,
   description TEXT,
   created DATE,
   done BOOLEAN
);