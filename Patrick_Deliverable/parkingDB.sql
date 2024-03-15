CREATE TABLE ParkingLot(
spotID varchar(10) unique PRIMARY KEY,
vehiclePlate varchar(50) unique,
vehicleDesc varchar(250),
isOccupied BOOLEAN NOT NULL,
idleTime int
)

CREATE TABLE ticketReg(


)
