package ai.smart.ix.repository;

import ai.smart.ix.FeatureCategory;
import ai.smart.ix.model.SmartFeature;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SmartFeatureRepository implements PanacheRepository<SmartFeature> {
    public Uni<SmartFeature> findByName(String name){
        return find("name", name).firstResult();
    }

    public Uni<List<SmartFeature>> findByModelType(String modelType){
        return list("modelType", modelType);
    }

    public Uni<List<SmartFeature>> findByCategory(FeatureCategory category){
        return list("category", category.name());
    }

    public Uni<List<SmartFeature>> findByModelTypeAndCategory(String modelType, FeatureCategory category){
        return list("modelType =?1 and category = ?2", modelType, category.name());
    }
}