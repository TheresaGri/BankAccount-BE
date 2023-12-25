package com.bankApplication.BankAccountBE.runner;

import com.bankApplication.BankAccountBE.models.*;
import com.bankApplication.BankAccountBE.repositories.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class UserPopulator {

    @Bean
    ApplicationRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode, PublicUserRepository publicUserRepository, SavingAccountRepository savingAccountRepository, TransactionRepository transactionRepository) {
        return args -> {
            Role admin = roleRepository.save(new Role("ADMIN"));
            Role user = roleRepository.save(new Role("USER"));
            Set<Role> roles = new HashSet<>();
            roles.add(user);
            roles.add(admin);
            Set<Role> roles1 = new HashSet<>();
            roles1.add(user);
            PublicUser publicUser = new PublicUser(1, "Theresa", "Grimus");
            PublicUser publicUser2 = new PublicUser(2, "Test", "testSurname");
            publicUserRepository.save(publicUser);
            publicUserRepository.save(publicUser2);
            User user1 = new User("1234", passwordEncode.encode("password"), roles);
            User user2 = new User("3456", passwordEncode.encode("password2"), roles1);

            userRepository.save(user1);
            userRepository.save(user2);
            SavingAccount savingAccount1 = new SavingAccount(3000,1);
            SavingAccount savingAccount2 = new SavingAccount(9000,2);
            savingAccountRepository.save(savingAccount1);
            savingAccountRepository.save(savingAccount2);

            Transaction transaction1 = new Transaction(1, LocalDateTime.now(),300,"Spar", TransactionType.OUTGOING);
            Transaction transaction2 = new Transaction(1, LocalDateTime.now(),1000,"Salary", TransactionType.INCOMING);
            Transaction transaction3 = new Transaction(2, LocalDateTime.now(),450,"Test", TransactionType.OUTGOING);
            transactionRepository.save(transaction1);
            transactionRepository.save(transaction2);
            transactionRepository.save(transaction3);
        };
    }
}
