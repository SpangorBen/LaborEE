CREATE TABLE employees (
   id SERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(255) UNIQUE NOT NULL,
   phone VARCHAR(20),
   department VARCHAR(100),
   position VARCHAR(100)
);