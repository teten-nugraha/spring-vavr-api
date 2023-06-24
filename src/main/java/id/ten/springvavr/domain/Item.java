package id.ten.springvavr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Item(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name
) {

}