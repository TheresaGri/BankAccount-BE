package com.bankApplication.BankAccountBE.runner;

import com.bankApplication.BankAccountBE.models.PublicUser;
import com.bankApplication.BankAccountBE.models.Role;
import com.bankApplication.BankAccountBE.models.User;
import com.bankApplication.BankAccountBE.repositories.PublicUserRepository;
import com.bankApplication.BankAccountBE.repositories.RoleRepository;
import com.bankApplication.BankAccountBE.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class UserPopulator {

    @Bean
    ApplicationRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode, PublicUserRepository publicUserRepository) {
        return args -> {
            Role admin = roleRepository.save(new Role("ADMIN"));
            Role user = roleRepository.save(new Role("USER"));
            Set<Role> roles = new HashSet<>();
            roles.add(user);
            roles.add(admin);
            PublicUser publicUser = new PublicUser(1, "Theresa", "Grimus");
            publicUserRepository.save(publicUser);
            User user1 = new User("Theresa", passwordEncode.encode("password"), roles);
            userRepository.save(user1);

        };
    }
}
