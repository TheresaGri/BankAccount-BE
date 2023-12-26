package com.bankApplication.BankAccountBE.runner;

import com.bankApplication.BankAccountBE.models.*;
import com.bankApplication.BankAccountBE.repositories.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class UserPopulator {

    @Bean
    ApplicationRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode, PublicUserRepository publicUserRepository, SavingAccountRepository savingAccountRepository, TransactionRepository transactionRepository, GiroAccountRepository giroAccountRepository) {
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

            Transaction transaction1 = new Transaction(1, LocalDateTime.now(),400,"Test1", TransactionType.OUTGOING);
            Transaction transaction2 = new Transaction(1, LocalDateTime.now(),1500,"Test2", TransactionType.INCOMING);
            List<Transaction> transactionList1 = new ArrayList<>();
            transactionList1.add(transaction1);
            transactionList1.add(transaction2);
            transactionRepository.save(transaction1);
            transactionRepository.save(transaction2);
            Transaction transaction3 = new Transaction(2, LocalDateTime.now(),400,"savingAccountTest1", TransactionType.OUTGOING);
            Transaction transaction4 = new Transaction(2, LocalDateTime.now(),1500,"savingAccountTest2", TransactionType.INCOMING);
            List<Transaction> transactionList2 = new ArrayList<>();
            transactionList2.add(transaction3);
            transactionList2.add(transaction4);
            transactionRepository.save(transaction3);
            transactionRepository.save(transaction4);
            SavingAccount savingAccount1 = new SavingAccount(transactionList1,1);
            SavingAccount savingAccount2 = new SavingAccount(transactionList2, 2);
            savingAccountRepository.save(savingAccount1);
            savingAccountRepository.save(savingAccount2);

            Transaction transaction5 = new Transaction(1, LocalDateTime.now(),300,"Spar", TransactionType.OUTGOING);
            Transaction transaction6 = new Transaction(1, LocalDateTime.now(),1000,"Salary", TransactionType.INCOMING);
            Transaction transaction7 = new Transaction(2, LocalDateTime.now(),450,"Test", TransactionType.OUTGOING);
            transactionRepository.save(transaction5);
            transactionRepository.save(transaction6);
            transactionRepository.save(transaction7);
            List<Transaction> transactionListGiro1 = new ArrayList<>();
            List<Transaction> transactionListGiro2 = new ArrayList<>();
            transactionListGiro1.add(transaction5);
            transactionListGiro1.add(transaction6);
            transactionListGiro2.add(transaction7);
            GiroAccount giroAccount1 = new GiroAccount(transactionListGiro1,1);
            GiroAccount giroAccount2 = new GiroAccount(transactionListGiro2,2);
            giroAccountRepository.save(giroAccount1);
            giroAccountRepository.save(giroAccount2);
        };
    }
}
