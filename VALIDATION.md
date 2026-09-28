## Transaction API fixes

Transaction responses now include `category` and `type`. Creation validates description, category, a positive amount and a valid type, returning 201 on success and 400 for invalid input. Invalid type filters return 400 instead of 500.

Regression tests exercise the running HTTP API and assert invalid requests do not persist records. Added GitHub Actions to run `mvn verify` on pushes and pull requests.

Validation: `mvn verify` passed all 9 tests locally with Java 17. PostgreSQL deployment is outside this change; the tests use the project's H2 database.
