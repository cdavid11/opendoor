/*
   Hacked from http://code.google.com/p/rc-switch/
   by @justy to provide a handy RF code sniffer

   Adjustments for the DoorSensor reed switch made by Chad Davidson
 */

#include "../rc-switch/RCSwitch.h"
#include <stdlib.h>
#include <stdio.h>
#include <iostream>
#include <fstream>


RCSwitch mySwitch;



int main(int argc, char *argv[]) {

	std::ofstream myfile;

	// This pin is not the first pin on the RPi GPIO header!
	// Consult https://projects.drogon.net/raspberry-pi/wiringpi/pins/
	// for more information.
	int PIN = 2;

	if(wiringPiSetup() == -1) {
		printf("wiringPiSetup failed, exiting...");
		return 0;
	}

	int pulseLength = 0;
	if (argv[1] != NULL) pulseLength = atoi(argv[1]);

	mySwitch = RCSwitch();
	if (pulseLength != 0) mySwitch.setPulseLength(pulseLength);
	mySwitch.enableReceive(PIN);  // Receiver on interrupt 0 => that is pin #2

	int received = 0;
	while(1) {

		if (mySwitch.available()) {

			int value = mySwitch.getReceivedValue();

			if (value == 0) {
				printf("Unknown encoding\n");
			} else if (value == received){
				printf("Duplicate\n");
			}else{    
				myfile.open("open_events.txt");
				myfile << value << "\n";
				myfile.close();
				printf("Received %i\n", value);
				received = value;
			}



			mySwitch.resetAvailable();


		}


	}
	exit(0);


}

