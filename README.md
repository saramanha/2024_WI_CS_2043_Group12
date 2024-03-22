## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

## Parking Classes Spike
Spike Goal: Explore the best way to organize classes related to parking and identify potential issues.

Spike Outcome: We found that we need at least four classes: ParkingSpot, Ticket, Registration, and Payment. To handle time, we used java.util.concurrent.TimeUnit but it is not clear if that is the best way to do it. In future designs we must be careful about how we are handling time, considering we must 'fast forward' time to simulate weeks and months of parking lot operation. Due to the limitations of the timebox in this spike activity, we were not able to create a driver or test these classes. The registration class could also include additional methods. 
