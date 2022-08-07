package com.tech.loader.models;

import com.univocity.parsers.annotations.Parsed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDataCsv {
    @Parsed(field = "username")
    private String userName;

    @Parsed(field = "password")
    private String password;

    @Parsed(field = "value")
    private String value;
}
