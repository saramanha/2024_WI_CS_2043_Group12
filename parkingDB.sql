CREATE TABLE ParkingLot(
spotID varchar(10) unique PRIMARY KEY,
isOccupied BOOLEAN NOT NULL,
vehiclePlate varchar(50) unique,
vehicleDesc varchar(250),
idleTime int
)
