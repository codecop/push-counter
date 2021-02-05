package org.codecop.pushcounter;

import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Random;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

import spark.Spark;

public class ApplicationSetupExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    @Target({ PARAMETER })
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface Port {

    }

    private final int port = 5000 + new Random().nextInt(5000);

    @Override
    public void beforeEach(ExtensionContext context) {
        System.getProperties().put("PORT", "" + port);
        Main.main(new String[0]);
        Spark.awaitInitialization();
    }

    @Override
    public void afterEach(ExtensionContext context) throws InterruptedException {
        Spark.stop();
        System.getProperties().remove("PORT");
        // Spark.awaitTermination();
        Thread.sleep(250);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().isAnnotationPresent(Port.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return port;
    }

}