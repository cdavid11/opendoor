# opendoor
A series of programs that implementing a "smart" door sensor

# Setup
API - NodeJS Server that implements RESTful API w/ MongoDB support. Runs on cloud server

Arduino Sketch -- Run Arduino sketch on Arduino microcontroller

DoorOpenAndroid -- Android Studio project that will compile and run simple Android Application on phone or emulator

RPi_files -- It's given that wiringPi and 433Utils libraries have been installed in a directory on the Raspberry Pi. In the 433Utils/RPi_utils directory, replace the Makefile and add DoorSensor.cpp and read_door.py
