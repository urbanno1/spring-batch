package cs544.cs.mum.edu.batchupload.serviceImpl;

import cs544.cs.mum.edu.batchupload.model.Role;
import cs544.cs.mum.edu.batchupload.repository.RoleRepository;
import cs544.cs.mum.edu.batchupload.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
