CREATE DATABASE ParkingLotDB;

USE ParkingLotDB;

CREATE TABLE ParkingLot(
  spotID varchar(2) unique PRIMARY KEY,
  isOccupied BOOLEAN NOT NULL,
  vehiclePlate varchar(7) unique,
  ticketId INT,
  checkInTime DATETIME,
  checkOutTime DATETIME,
  durationInHours DOUBLE
);

CREATE USER 'testuser'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON ParkingLotDB.* TO 'testuser'@'localhost';
FLUSH PRIVILEGES;
