#!/usr/bin/env bash
set -e

psql -v ON_ERROR_STOP=1 --username postgres --dbname postgres <<-EOSQL
    ALTER ROLE postgres WITH ENCRYPTED PASSWORD 'bergen';
    CREATE USER bergen WITH ENCRYPTED PASSWORD 'bergen';
    CREATE DATABASE web WITH OWNER bergen;
    GRANT ALL ON DATABASE web TO bergen;
EOSQL