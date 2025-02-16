-- Create multiple databases only if they do not already exist
CREATE DATABASE IF NOT EXISTS product;
CREATE DATABASE IF NOT EXISTS users;

-- Create a user only if it does not exist
CREATE USER IF NOT EXISTS 'raj@localhost' IDENTIFIED BY 'pass';

-- Grant privileges to the user for the databases
GRANT ALL PRIVILEGES ON product.* TO 'raj';
GRANT ALL PRIVILEGES ON users.* TO 'raj';
FLUSH PRIVILEGES;