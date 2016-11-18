# Time zone specific times in java.time

A time zone is identified by a ZoneId. An actual offset from GMT is represented by a ZoneOffset, which can be derived from a ZoneId and an actual time (eg. ZoneOffset depends on daylight saving time).

- ZonedDateTime stores the ZoneId and a LocalDateTime. When converting to Instant timeline, this is not always simple, time may not exist (in the gap) or may be ambiguous (exist in two offsets)
- OffsetDateTime stores the ZoneOffset and a LocalDateTime. This always uniquely represents a point in the Instant timeline.