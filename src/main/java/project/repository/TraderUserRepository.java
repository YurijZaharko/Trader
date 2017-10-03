package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.entity.TraderUser;

public interface TraderUserRepository  extends JpaRepository<TraderUser, Long>, JpaSpecificationExecutor<TraderUser>{
    TraderUser findByUsername(String s);
}
