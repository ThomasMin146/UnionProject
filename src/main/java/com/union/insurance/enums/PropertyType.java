package com.union.insurance.enums;

import lombok.Getter;

@Getter
public enum PropertyType {
    BYT("Byt"),
    RODINNY_DOM_MUROVANY("Rodinný dom - murovaný"),
    RODINNY_DOM_DREVENY("Rodinný dom - drevený");

    private final String displayName;

    PropertyType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
