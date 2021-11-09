#!/bin/sh

mvn clean 
mvn generate-sources
mvn compile assembly:single