package com.bankApplication.BankAccountBE.security;

import com.bankApplication.BankAccountBE.models.User;
import com.bankApplication.BankAccountBE.repositories.UserRepository;
import com.bankApplication.BankAccountBE.services.UserService;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Supplier;

@Component
public class UserSecurity implements AuthorizationManager<RequestAuthorizationContext> {

    private final UserService userService;

    public UserSecurity(UserService userService) {
        this.userService = userService;
    }

    @Override
    public AuthorizationDecision check(Supplier authenticationSupplier, RequestAuthorizationContext ctx) {
        // get {userId} from the request
        Long userId = Long.parseLong(ctx.getVariables().get("userId"));
        System.out.println(userId);
        Authentication authentication = (Authentication) authenticationSupplier.get();
        System.out.println(authentication);
        System.out.println(hasUserId(authentication,userId));
        System.out.println(new AuthorizationDecision(hasUserId(authentication, userId)));
        return new AuthorizationDecision(hasUserId(authentication, userId));
    }

    //TODO: is this correctly implemented? does not seem like it
    public boolean hasUserId(Authentication authentication, Long userId) {
             return Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .map(principal -> {
                    if (principal instanceof Jwt) {
                        Jwt jwt = (Jwt) principal;
                        String userName = jwt.getSubject();
                        return userId.equals(userService.findIdByUsername(userName));
                    }

                    if (principal instanceof User) {
                        User user = (User) principal;
                        return userId.equals(user.getId());
                    }

                    return false;
                })
                .orElse(false);
    }
}
