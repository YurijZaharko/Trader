package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.TemplateLink;

public interface TemplateLinkRepository extends JpaRepository<TemplateLink, Integer> {
    TemplateLink findByLinkName(String linkName);
}
