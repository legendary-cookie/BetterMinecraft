#!/bin/bash
./gradlew clean shadowJar
scp build/libs/Skyblock-*.jar dockeruser@docker1.getcom.de:/opt/docker/dockercraft/betterminecraft.jar
ssh -t dockeruser@docker1.getcom.de "sudo cp /opt/docker/dockercraft/betterminecraft.jar /opt/docker/dockercraft/data/testserver/plugins/betterminecraft.jar"
ssh -t dockeruser@docker1.getcom.de "cd /opt/docker/dockercraft && ./reload_testserver.sh"
