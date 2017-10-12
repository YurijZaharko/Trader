package project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TemplateLink {
    private Long id;
    private String linkName;
    private String templateName;

    public TemplateLink() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemplateLink)) return false;

        TemplateLink that = (TemplateLink) o;

        return linkName != null ? linkName.equals(that.linkName) : that.linkName == null;
    }

    @Override
    public int hashCode() {
        return linkName != null ? linkName.hashCode() : 0;
    }
}
