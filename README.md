## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

## User Story Arrival
This user story encompasses the arrival of a customer at the parking garage. The parking machine program prioritizes user-friendly functionality. It must not allow the incorrect input, such as invalid parking spot, plate number over 7 characters, repeated plate number during overlapping periods, and durations exceeding 24 hours. It also automatically records check-in time upon payment confirmation, calculates precise parking fees, and displays the ticket on the screen.

## Changes
- Renamed `Registration` class to `ParkingLot`, it now handles multiple spots as well as the previous functionality in `Registration` therby simplifying `Main`.
- Added functionality for user to choose a duration to park
- Included unit tests in src/test/java