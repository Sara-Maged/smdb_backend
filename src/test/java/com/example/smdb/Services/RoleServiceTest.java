package com.example.smdb.Services;

import com.example.smdb.Entities.RoleEntity;
import com.example.smdb.Entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RoleServiceTest {

    RoleService roleService = Mockito.mock(RoleService.class);

    @Test
    void getRoles_AllRolesFetched_True() {
        RoleEntity admin = new RoleEntity("ADMIN");
        RoleEntity user = new RoleEntity("USER");

        List<RoleEntity> roles = new ArrayList<>(Arrays.asList(admin, user));
        Mockito.when(roleService.getRoles()).thenReturn(roles);

        assertEquals(2, roleService.getRoles().size());
        assertEquals(roleService.getRoles(), roles);
    }

    @Test
    void addNewRole_MethodCalled_True() {
        RoleEntity admin = new RoleEntity("ADMIN");
        roleService.addNewRole(admin);

        assertEquals("ADMIN", admin.getName());
        verify(roleService, times(1)).addNewRole(admin);
    }
}