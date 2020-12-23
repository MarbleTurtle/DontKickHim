package com.doNotKick;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KickMode {
    All("All Ranks"),
    Some("Higher or equal Ranks"),
    Every("Everyone");

    private final String name;

    @Override
    public String toString()
    {
        return name;
    }
}
