package com.example.smdb.Services;

import com.example.smdb.Entities.RoleEntity;
import com.example.smdb.Entities.UserEntity;
import com.example.smdb.Respiratories.RoleRepository;
import com.example.smdb.Respiratories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //Getting All Roles
    public List<RoleEntity> getRoles(){
        return roleRepository.findAll();
    }

    //Adding a new Role
    public void addNewRole(RoleEntity role) {
        Optional<RoleEntity> roleByName = Optional.ofNullable(roleRepository
                .findByName(role.getName()));

        if(roleByName.isPresent()){
            throw new IllegalStateException("Role Already Exists");
        }
        roleRepository.save(role);
    }
}
