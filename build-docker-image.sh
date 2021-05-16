#!/bin/sh

./gradlew build -x test
./gradlew createDockerImage
./gradlew showImageName