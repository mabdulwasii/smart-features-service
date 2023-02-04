package ai.smart.ix.model;

import ai.smart.ix.FeatureCategory;
import ai.smart.ix.utils.ValidEnumValue;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "smart_feature")
@Table(indexes = {
        @Index(columnList = "name"),
        @Index(columnList = "type"),
        @Index(columnList = "category"),
        @Index(name = "fn_type_category_index", columnList = "type, category")
})
public class SmartFeature extends PanacheEntity {
    @NotEmpty(message = "Name is required")
    @Column(unique = true, length = 50, nullable = false)
    private String name;

    @NotEmpty(message = "Feature model type is required")
    @Column(name = "type", length = 50, nullable = false)
    private String modelType;

    @ValidEnumValue(enumClass = FeatureCategory.class)
    @Column(nullable = false, length = 20)
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "SmartFeature{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", modelType='" + modelType + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}