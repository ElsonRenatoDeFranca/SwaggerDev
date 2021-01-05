package com.rest.openapi.swagger.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;

    @NonNull
    private String personalId;
    @NonNull
    private String name;
    @NonNull
    private Integer age;
}
