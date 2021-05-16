#!/bin/sh

./gradlew build
./gradlew createDockerImage
./gradlew showImageName