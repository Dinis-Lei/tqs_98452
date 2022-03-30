# TQS 98452

## a)

file: A_EmployeeRepositoryTest.java

    assertThat(fromDb).isNotNull();
    assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail();

## b)

The tests on B_EmployeeService_UnitTest

## c)

<b>@Mock</b> Anotation allowa to mock a class or an interface and to record and verify behaviors on it.

<b>@MockBean</b> Annotation allows to add Mockito mocks in a Spring ApplicationContext.
If a bean, compatible with the declared class is present in the context, it will be replaced by the mock. If it is not present, the mock will be added as a bean in the context

## d)

The file "application-integrationtest.properties" is there to setup the properties needed to connect to a database so when doing integration tests with a real data base (instead of H2 memory database) the tests know how to connect to it.

## e)

Test C focus on testing the connection to the api, mocking every other interaction inside the application.
Test D is and Integration test that focus on testing the api via a servlet entry point for tests
Test E is like test D but the entry proint is an API client