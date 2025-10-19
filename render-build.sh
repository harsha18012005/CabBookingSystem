#!/usr/bin/env bash
sdk install java 17.0.10-tem
sdk use java 17.0.10-tem
mvn clean package -DskipTests
