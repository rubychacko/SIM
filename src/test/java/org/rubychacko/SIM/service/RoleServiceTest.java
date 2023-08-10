package org.rubychacko.SIM.service;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.rubychacko.SIM.model.Role;
import org.rubychacko.SIM.repository.RoleRepository;

public class RoleServiceTest {

    private RoleService service;
    private RoleRepository repository;

    @BeforeEach
    public void init() {
        repository = Mockito.mock(RoleRepository.class);
        service = new RoleServiceImpl(repository);
    }

    @Test
    public void testSaveRole() {

        service.saveRole(newRole());
        Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any(Role.class));
    }

    @Test
    public void testFindRoleById() {

        Mockito.doReturn(newRole()).when(repository).findRoleByName(ArgumentMatchers.anyString());

        val response = service.findRoleByRoleName("id");
        Assertions.assertNotNull(response);
    }

    private Role newRole() {
        return new Role("role");
    }
}
