package baka.annecy.scraper.application.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baka.annecy.scraper.application.user.command.CreateUserCommand;
import baka.annecy.scraper.configuration.exception.ApplicationCredentialsException;
import baka.annecy.scraper.configuration.exception.ApplicationException;
import baka.annecy.scraper.configuration.security.SecurityUtils;
import baka.annecy.scraper.domain.user.User;
import baka.annecy.scraper.domain.user.User.Role;
import baka.annecy.scraper.domain.user.UserRepository;
import baka.annecy.scraper.domain.user.dto.UserDto;

@Service
@Transactional
public class UserService {

  @Autowired UserRepository userRepository;
  @Autowired SecurityUtils securityUtils;

  public UserDto createUser(CreateUserCommand command) {
    User user = userRepository.findByLogin(command.getLogin());

    if (user != null) {
      throw new ApplicationException("login already used");
    }

    user =
        new User(
            command.getLogin(), SecurityUtils.encodeToSHA512(command.getPassword()), Role.USER);

    userRepository.save(user);

    return new UserDto(user);
  }

  public UserDto getUserByLogin(String login) {
    User user = userRepository.findByLogin(login);

    return user != null ? new UserDto(user) : null;
  }

  public UserDto login(CreateUserCommand command) {
    User user = userRepository.findByLogin(command.getLogin());

    if (user == null
        || !SecurityUtils.encodeToSHA512(command.getPassword()).equals(user.getPass())) {
      throw new ApplicationCredentialsException("invalid-credentials");
    }

    return new UserDto(user);
  }
}
