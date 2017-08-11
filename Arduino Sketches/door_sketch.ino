#include <RCSwitch.h>
/*

Rameez Saiyid

Reed Switch and Arduino

4.9.15

*/

RCSwitch mySwitch = RCSwitch();
const int switchPin = 2;
const int ledPin = 13;
int code = 28728;
int just_transmitted = 0;

void setup() {
Serial.begin(9600);
mySwitch.enableTransmit(10);


pinMode(switchPin, INPUT);

pinMode(ledPin, OUTPUT);

digitalWrite(switchPin, HIGH);

}

void loop() {



if(digitalRead(switchPin) == LOW){

if (just_transmitted == 1){ code++; just_transmitted = 0; }
digitalWrite(ledPin, LOW);

}

else{
  
just_transmitted = 1;
digitalWrite(ledPin, HIGH);
mySwitch.send(code, 24);

}

}
