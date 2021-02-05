# Push Counter

Counter dash board of positive and negative push events during Coding Dojo "championships".

Running the project
-------------------

Once you have configured and installed Maven, Java Development Kit and Push Counter, you can start the application by running this command from the root folder of the project:

```bash
mvn exec:java
```

The application will then be available at [localhost](http://localhost:4567) port 4567.

## Running the project in Docker

The application is published on the [docker hub](https://hub.docker.com/r/codecop/push-counter). You can run it like this:

```bash
docker run -p4567:4567 "codecop/push-counter"
```

The application will then be available at [localhost](http://localhost:4567) port 4567.

## License

Copyright (c) 2021, Peter Kofler. All rights reserved.
[New BSD License](https://opensource.org/licenses/BSD-3-Clause), see `LICENSE` in repository.
