package com.vitor.cordeiro.teste.quarkus.util;

import org.eclipse.microprofile.config.ConfigProvider;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConstantHelper {

    private static String googleUrl = getEnvValue("GOOGLE_URL");

    private static String getEnvValue(String key) {
        if(System.getenv(key) == null)
            return ConfigProvider.getConfig().getValue(key, String.class);
        return System.getenv(key);
    }

    public static String getGoogleUrl() {
        return getEnvValue("GOOGLE_URL");
    }

}
