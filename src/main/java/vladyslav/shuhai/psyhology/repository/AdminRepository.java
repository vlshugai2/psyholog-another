package vladyslav.shuhai.psyhology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vladyslav.shuhai.psyhology.entity.Admin;
import vladyslav.shuhai.psyhology.entity.Event;
import vladyslav.shuhai.psyhology.entity.User;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByUsername(String username);
    boolean existsByUsername(String username);
}