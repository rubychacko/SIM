package org.rubychacko.SIM.service;

import org.rubychacko.SIM.model.Role;
import org.rubychacko.SIM.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Contains service methods to manage the role services.
 *
 * @author Ruby Chacko
 */
@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // To save the role information
    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    //To find the role by RoleName
    @Override
    @Transactional
    public Role findRoleByRoleName(String name) {
        return roleRepository.findRoleByName(name);
    }

    // To get a list of all Roles
    @Override
    public List<Role> getAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    // To get list of all roles by UserId
    @Override
    public List<Role> getRolesByUser(long id) {
        return roleRepository.findRoleByUser(id);
    }
}