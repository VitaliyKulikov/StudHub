package org.hackathon.controller;

import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;

import java.util.Collections;
import java.util.List;

public final class ControllerHelper {

    private ControllerHelper() {
    }


    static <T> Resources<?> getResource(Iterable<T> active, Class<T> clazz) {
        Resources<?> resources;
        if (!active.iterator().hasNext()) {
            EmbeddedWrappers wrappers = new EmbeddedWrappers(false);
            EmbeddedWrapper wrapper = wrappers.emptyCollectionOf(clazz);
            List<Object> content = Collections.singletonList(wrapper);

            resources = new Resources<>(content);
        } else {
            resources = new Resources<>(active);
        }
        return resources;
    }
}
