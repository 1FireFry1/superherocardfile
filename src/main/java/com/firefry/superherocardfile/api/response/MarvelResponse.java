package com.firefry.superherocardfile.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class MarvelResponse {
    private String description;
}
