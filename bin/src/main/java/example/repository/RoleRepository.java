package example.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository {
    List<String> findAll();
}
