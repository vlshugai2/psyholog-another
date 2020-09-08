package vladyslav.shuhai.psyhology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vladyslav.shuhai.psyhology.entity.Newsletter;

public interface NewsletterRepository extends JpaRepository<Newsletter,Long> {
}
