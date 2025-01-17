# Airport Traffic Controller
## Brief
Write A system to assist with controlling the flights that land at different airports

## Requirements
- The system manages three airports with IDs: "LGW", "EMA" and "MAN".
- An aircraft cannot land at the same airport until 60 seconds after the last aircraft landed.
- The system will ask the user where the aircraft will land (i.e. one of the airport IDs mentioned above).
- If the landing requirement is met, the system will print a message confirming the landing and will prompt the user again for another airport.
- If the landing requirement is not met, the system will print a countdown with the time remaining for the next landing. When the time is up, it will then land the plane at that airport, print a message confirming the landing and will prompt the user again for another airport.

## User Interface
### Basic
Individually land planes at airports from a predefined list

### Manual Batch
Input a batch of planes to land at airports at specified delay times

### Automated Batch
Use a CSV to generate a batch of flight plans and simulate their landings

### Settings
Specify a custom minimum delay between landings