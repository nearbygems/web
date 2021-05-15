#!/bin/sh

cd "$(dirname "$0")"

docker-compose down

docker network create web

docker run --rm -i \
  -v "$PWD/volumes:/volumes" \
  busybox:1.31.0 \
  find /volumes/ -maxdepth 1 -mindepth 1 -exec rm -rf {} \;

docker-compose up -d