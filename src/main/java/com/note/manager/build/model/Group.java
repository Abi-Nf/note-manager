package com.note.manager.build.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Group implements Serializable {
    private Long id;
    private String name;
}
