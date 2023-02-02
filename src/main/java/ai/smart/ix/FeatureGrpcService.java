package ai.smart.ix;

import ai.smart.ix.repository.SmartFeatureRepository;
import com.google.protobuf.Empty;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService
public class FeatureGrpcService implements FeatureGrpc {

    private final SmartFeatureRepository repository;

    public FeatureGrpcService(SmartFeatureRepository repository) {
        this.repository = repository;
    }

    @Override
    public Uni<FeatureResponse> getFeatureById(FeatureByIdRequest request) {
        return null;
    }

    @Override
    public Uni<FeatureResponse> getFeatureByName(FeatureByNameRequest request) {
        return null;
    }

    @Override
    public Uni<FeatureListResponse> getFeatureByType(FeatureByTypeRequest request) {
        return null;
    }

    @Override
    public Uni<FeatureListResponse> getFeatureByCategory(FeatureByCategoryRequest request) {
        return null;
    }

    @Override
    public Uni<FeatureListResponse> getFeatureByTypeAndCategory(FeatureByTypeCategoryRequest request) {
        return null;
    }

    @Override
    public Uni<FeatureListResponse> getAllFeatures(Empty request) {
        return null;
    }

    @Override
    public Uni<FeatureListResponse> findFeatures(FindFeatureRequest request) {
        return null;
    }
}
