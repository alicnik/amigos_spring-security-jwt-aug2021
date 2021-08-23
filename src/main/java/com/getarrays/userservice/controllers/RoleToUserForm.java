package com.getarrays.userservice.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleToUserForm {
    private String username;
    private String roleName;
}
