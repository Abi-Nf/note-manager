package com.note.manager.build.Utils;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private int status;
    private String message;
    private Object object;
}
