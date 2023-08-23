package com.note.manager.build.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Subject {
    private Long id;
    private String name;
    private String description;
}
