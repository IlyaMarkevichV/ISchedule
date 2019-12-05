package backend.service.impl;

import backend.entity.Role;
import backend.repository.RoleRepository;
import backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Role> getAllRoles() {
        return repository.findAll();
    }
}
