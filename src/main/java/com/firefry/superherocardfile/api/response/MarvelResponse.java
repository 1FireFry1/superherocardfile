package com.firefry.superherocardfile.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class MarvelResponse {
    private final String id;
    private final String name;
    private final String description;
}
