package com.social.network.TestModel;

import java.security.Principal;

public class TestPrincipal implements Principal {

    @Override
    public String getName() {
        return "Zahar";
    }
}
