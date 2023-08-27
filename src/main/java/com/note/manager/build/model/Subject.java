package com.note.manager.build.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Subject {
    private long id;
    private String name;
    private String description;
}
