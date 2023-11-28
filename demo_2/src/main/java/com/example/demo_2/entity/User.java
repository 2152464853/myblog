package com.example.demo_2.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain=true)
public class User implements Serializable {
   private static final long serialVersionUID=1L;
   private Long id;
   private String username;
   private String avatar;
   private String email;
   private String password;
   private Integer status;
   private LocalDateTime created;
   private LocalDateTime last_login;

}
