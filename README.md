# NewsRoom Project
I am building a web platform for news content similar to news.google.com.
The most significant feature of my webapp will be the ability for users
to post comments externally on the content items and rate the content
across a variety of metrics. Users should be able to engage with news
media. The current polarization of our media signals the need for a
stronger feedback loop between media publications and their readers.

## content-base
This a open source project develops a REST service to provide content from
Google Cloud Datastore for UI consumption. This service will fetch and bind
content containing news information, metadata from reviews, and user-
generated content such as comments, which may be stored in other structures.

Contentbase is developed with Java 8 and SpringBoot 2. This is a work-in-
progress, with many new features to be added for persistence layer and
cross-service integrations. The intended runtime is either VM or container.
