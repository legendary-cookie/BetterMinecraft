#!/bin/bash
./gradlew shadowJar
rm -f ~/mctestserver/betterminecraft.jar
cp build/libs/*-all.jar ~/mctestserver/betterminecraft.jar