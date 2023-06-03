package ru.nikulin.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class ScenarioContext {
    private static final Logger logger = LogManager.getLogger();
    private final Map<String, Object> container = new HashMap<>();

    public void setVar(String name, Object object) {
        container.put(name, object);
        logger.info("Variable was updated: " + name);
    }

    public <T> T getVar(String name) {
        try {
            return (T) container.get(name);
        } catch (ClassCastException exception) {
            return null;
        }
    }

}