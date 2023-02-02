package ai.smart.ix.model;

import ai.smart.ix.model.enumeration.FeatureCategory;
import ai.smart.ix.utils.ValidEnumValue;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class SmartFeature extends PanacheEntity {

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Feature model type is required")
    private String modelType;

    @ValidEnumValue(enumClass = FeatureCategory.class)
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}