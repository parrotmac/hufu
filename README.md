# HuFU: Headunit Feature Upgrader

## \*Note: This is still very much WIP, with minimal functionality \*

Hufu is designed to work with the impressive [Headunit Reloaded Emulator](https://play.google.com/store/apps/details?id=gb.xxy.hr&hl=en). While HUR is a great way to run Android Auto on an Android device, there are still rough corners when using a regular ol' tablet as a headunit. This is here HUFU comes in.

## Goals
  - Steering Wheel Controls Integration
  - ACC On/Off events
  - Brightness controls
  
## Hardware
This application will poll serial data from a microcontroller connected to the vehicle. Currently planned in-vehicle device is the Arduino Uno (R3) or similar. Connection will be via USB OTG.

Current targets are the Nexus 7 (2013) and the Kindle Fire 7.
