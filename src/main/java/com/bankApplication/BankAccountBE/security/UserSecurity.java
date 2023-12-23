package com.bankApplication.BankAccountBE.security;

import com.bankApplication.BankAccountBE.models.User;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Supplier;

@Component
public class UserSecurity implements AuthorizationManager<RequestAuthorizationContext> {
    @Override
    public AuthorizationDecision check(Supplier authenticationSupplier, RequestAuthorizationContext ctx) {
        // get {userId} from the request
        Long userId = Long.parseLong(ctx.getVariables().get("userId"));
        System.out.println(userId);
        Authentication authentication = (Authentication) authenticationSupplier.get();
        System.out.println(authentication);
        System.out.println(hasUserId(authentication,userId));
        return new AuthorizationDecision(hasUserId(authentication, userId));
    }

    //TODO: is this correctly implemented?
    public boolean hasUserId(Authentication authentication, Long userId) {
        return Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .filter(principal -> principal instanceof User)
                .map(User.class::cast)
                .map(User::getId)
                .map(userId::equals)
                .orElse(false);
    }
}
