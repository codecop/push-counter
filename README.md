# Push Counter

Counter dash board of positive and negative push events during Coding Dojo "championships".

## Running the project

Once you have configured and installed Maven, the Java Development Kit and Push Counter sources, 
you can start the application by running this command from the root folder of the project:

```bash
mvn exec:java
```

The application will then be available at [localhost](http://localhost:4567) port 4567.

### Running the project in Docker

The application is published on the [docker hub](https://hub.docker.com/r/codecop/push-counter).
You can run its latest version like this:

```bash
docker run -p4567:4567 "codecop/push-counter"
```

The application will then be available at [localhost](http://localhost:4567) port 4567.

## Licenses

Push Counter Copyright (c) 2021, Peter Kofler. All rights reserved.
[New BSD License](https://opensource.org/licenses/BSD-3-Clause), see `LICENSE` in repository.

Images are [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/):
[Crown Coin by lorc](https://game-icons.net/1x1/lorc/crown-coin.html),
[Medal by lorc](https://game-icons.net/1x1/lorc/medal.html),
[Laurels Trophy by delapouite](https://game-icons.net/1x1/delapouite/laurels-trophy.html).
Sounds are [CC 0](http://creativecommons.org/publicdomain/zero/1.0/):
[Badge Coin Win by steaq](https://freesound.org/people/steaq/sounds/387232/).

## History

* 19.01. - 5.0h - initial version of counter application
* 22.01. - 1.0h - cleanup
* 22.01. - 1.0h - various client code variations, see [sample client](https://github.com/codecop/push-counter-client)
* 05.02. - 3.0h - refresh, JSON content type, copyright, version
* 07.02. - 1.0h - sound
* 16.02. - 1.0h - +/- links
