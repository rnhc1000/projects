## _Roadmap to Java Certification_ <br />

I started working on OCA and OCP Java Professional Developer Certification and decide to dive into it.
This is how I played with the challenges of OOP paradigm, Streams, Functional Interfaces, Lambdas,
Pattern Matching, Concurrency and others key Java concepts.

## _Table of contents_

- [_Overview_](#overview)
- [_Requirements_](#requirements)
- [_Project Structure_](#requirements)
- [_Howto Build and Run_](#requirements)
- [_Screenshot_](#screenshot)
- [_Links_](...)
- [_Built with_](#built-with)
- [_Code Snippet_](#requirements)
- [_Continued development_](#continued-development)
- [_Useful resources_](#useful-resources)
- [_Author_](#requirements)
- [_Portfolio_](#requirements)

## _Overview_

Here you will find the roadmap proposed using Java 21 where the focus is to tackle the Java language concepts and challenges.
<br />

## _Requirements_

- Java 17+
- Functional Programming
- TDD - Test Driven Development
- JUnit
- Java Vanilla tests
- JUnit fixtures
- Unit tests
- Mockito
- MockBean
- Database queries
- Spy Mock
- Test coverage
- Jacoco
- Gradle

<hr />

## _Project Structure_

- docs
    - javadocs
- src
    - main
    - java
        - br.dev.ferreiras.projects
            - VirtualThread
            - controller
                - handlers
    - resources
        - db.migration
        - certs
    - test
-

## _Howto Build and Run_

  ```

  
```

## _Screenshot_

[![](./)]()

## _Links_

- Live Site URL: <a href=https://docs.oracle.com" target="_blank">@java</a>

## _Built with_

[![My Skills](https://skillicons.dev/icons?i=java,spring,maven,postgres,redhat,idea,git,github,)](https://skillicons.dev)

## _Code Snippet_

```java
import java.util.List;

/**
 *
 * @author ricardo@ferreiras.dev.br
 * @version 1.1.12.01
 * @since 1.0
 *
 */

@ContextConfiguration
public class UserServiceTests {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private CustomUserUtil userUtil;

  private String existingUsername, nonExistingUsername;
  private UserEntity userEntity;
  private List<UserDetailsProjection> userDetails;

  @BeforeEach
  void setUp() throws Exception {

    existingUsername = "maria@gmail.com";
    nonExistingUsername = "ricardo@gmail.com";
    userEntity = UserFactory.createUserEntity();
    userDetails = UserDetailsFactory.createCustomAdminClientUser(existingUsername);

    Mockito.when(userRepository.findByUsername(existingUsername)).thenReturn(Optional.of(userEntity));
    Mockito.when(userRepository.findByUsername(nonExistingUsername)).thenThrow(UsernameNotFoundException.class);

    Mockito.when(userRepository.searchUserAndRolesByUsername(existingUsername)).thenReturn(userDetails);
    Mockito.when(userRepository.searchUserAndRolesByUsername(nonExistingUsername)).thenReturn(new ArrayList<>());

  }

  @Test
  public void authenticatedShouldReturnUserEntityWhenUserExists() {

    Mockito.when(userUtil.getLoggedUsername()).thenReturn(existingUsername);
    UserEntity user = userService.authenticated();

    Assertions.assertNotNull(user);
    Assertions.assertEquals(user.getUsername(), existingUsername);

  }

  @Test
  public void authenticatedShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {

    Mockito.doThrow(ClassCastException.class).when(userUtil).getLoggedUsername();

    Assertions.assertThrows(UsernameNotFoundException.class, () -> {
      userService.authenticated();
    });

  }

  @Test
  public void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {

    UserDetails user = userService.loadUserByUsername(existingUsername);

    Assertions.assertNotNull(user);
    Assertions.assertEquals(user.getUsername(), existingUsername);

  }

  @Test
  public void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {

    Assertions.assertThrows(UsernameNotFoundException.class, () -> {
      userService.loadUserByUsername(nonExistingUsername);
    });

  }
}

``` 

## _Continued development_


### _Useful resources_

- [https://spring.io] Awesome Java framework!.
- [https://start.spring.io/]  Handy startup tool.
- [https://mvnrepository.com] Tools that help tackle the beast

## _Author_

<a href="mailto:ricardo@ferreiras.dev.br">Ricardo Ferreira</a>

## - _Portfolio_

<a href="https://www.ferreiras.dev.br" target="_blank">My Portfolio...</a>
