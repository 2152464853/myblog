package com.example.demo_2.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain=true)
public class Blog implements Serializable {
    private static final long serialVersionUID=1L;
    private Long id;
    private Long user_id;
    private String title;
    private String description;
    private String content;
    private LocalDateTime created;
    private Integer status;
}
