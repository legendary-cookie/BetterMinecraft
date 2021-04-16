#!/bin/bash
./gradlew build
rm -f ~/mctestserver/betterminecraft.jar
cp build/libs/*.jar ~/mctestserver/betterminecraft.jar