package ai.smart.ix.service;

import ai.smart.ix.*;
import ai.smart.ix.repository.SmartFeatureRepository;
import ai.smart.ix.utils.DtoTransformer;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

import javax.transaction.Transactional;

@GrpcService
public class FeatureGrpcService implements FeatureGrpc {
    private final SmartFeatureRepository repository;

    public FeatureGrpcService(SmartFeatureRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Uni<FeatureResponse> createFeature(FeatureRequest request) {
        return repository.persistAndFlush(
                DtoTransformer.buildSmartFeature(request)
        ).onItem().transform(DtoTransformer::buildFeatureResponse);
    }

    @Override
    public Uni<FeatureListResponse> getFeatureByModelTypeAndCategory(FeatureByModelTypeCategoryRequest request) {
        return repository.findByModelTypeAndCategory(request.getModelType(), request.getCategory())
                .onItem().transform(DtoTransformer::buildFeatureListResponse);
    }

    @Override
    public Uni<FeatureListResponse> getFeatures(GetFeatureRequest request) {
        if (request.hasId()) {
            return repository.findById(request.getId())
                    .onItem().transform(DtoTransformer::buildFeatureListResponse);
        } else if (request.hasName()) {
            return repository.findByName(request.getName())
                    .onItem().transform(DtoTransformer::buildFeatureListResponse);
        } else if (request.hasModelType()) {
            return repository.findByModelType(request.getModelType())
                    .onItem().transform(DtoTransformer::buildFeatureListResponse);
        } else if (request.hasCategory()) {
            return repository.findByCategory(request.getCategory())
                    .onItem().transform(DtoTransformer::buildFeatureListResponse);
        } else {
            return repository.listAll().onItem().transform(DtoTransformer::buildFeatureListResponse);
        }
    }
}