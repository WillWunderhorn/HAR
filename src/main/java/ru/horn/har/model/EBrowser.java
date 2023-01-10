package ru.horn.har.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;

@Getter
@AllArgsConstructor
public enum EBrowser {
    YANDEX(Set.of("YaBrowser", "Yowser", "Yandex")),
    GOOGLE(Set.of("Chrome")),
    MOZILLA(Set.of("Mozilla")),
    UNKNOWN(Collections.emptySet());

    private final Set<String> nameVariations;

    public static EBrowser of(String name) {
        if (name == null || name.isEmpty()) return UNKNOWN;

        for (EBrowser value : values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }

            for (String nameVariation : value.nameVariations) {
                if (nameVariation.equalsIgnoreCase(name)) {
                    return value;
                }
            }
        }
        return UNKNOWN;
    }
}
