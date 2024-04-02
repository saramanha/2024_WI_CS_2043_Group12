CREATE DATABASE ParkingLotDB;

USE ParkingLotDB;

CREATE TABLE ParkingLot(
  spotID varchar(2) UNIQUE PRIMARY KEY,
  isOccupied BOOLEAN NOT NULL,
  vehiclePlate varchar(7) UNIQUE,
  ticketId INT UNIQUE,
  checkInTime DATETIME,
  checkOutTime DATETIME,
  durationInHours DOUBLE
);

CREATE USER 'testuser'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON ParkingLotDB.* TO 'testuser'@'localhost';
FLUSH PRIVILEGES;
