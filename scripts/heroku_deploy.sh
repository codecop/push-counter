#!/usr/bin/env bash
heroku deploy:jar target/push-counter-1.0-SNAPSHOT-jar-with-dependencies.jar --app push-counter
