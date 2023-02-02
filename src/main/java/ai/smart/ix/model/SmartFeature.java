package ai.smart.ix.model;

import ai.smart.ix.model.enumeration.FeatureCategory;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class SmartFeature extends PanacheEntity {
    private String name;
    private String type;

    @Enumerated(EnumType.STRING)
    private FeatureCategory category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FeatureCategory getCategory() {
        return category;
    }

    public void setCategory(FeatureCategory category) {
        this.category = category;
    }
}