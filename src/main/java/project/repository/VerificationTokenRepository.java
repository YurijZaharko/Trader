package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
