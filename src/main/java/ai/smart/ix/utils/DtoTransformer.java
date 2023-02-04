package ai.smart.ix.utils;

import ai.smart.ix.FeatureCategory;
import ai.smart.ix.FeatureListResponse;
import ai.smart.ix.FeatureRequest;
import ai.smart.ix.FeatureResponse;
import ai.smart.ix.exception.GenericException;
import ai.smart.ix.model.SmartFeature;

import java.util.List;
import java.util.stream.Collectors;

import static ai.smart.ix.utils.AppConstants.INVALID_REQUEST;
import static ai.smart.ix.utils.AppConstants.INVALID_SMART_FEATURE;

public class DtoTransformer {
    private DtoTransformer() {
    }

    public static FeatureResponse buildFeatureResponse(SmartFeature smartFeature) {
        ensureNotNull(smartFeature, INVALID_SMART_FEATURE);
        return FeatureResponse.newBuilder().setId(smartFeature.getId())
                .setName(smartFeature.getName())
                .setModelType(smartFeature.getModelType())
                .setCategory(FeatureCategory.valueOf(smartFeature.getCategory())).build();
    }

    public static FeatureListResponse buildFeatureListResponse(List<SmartFeature> smartFeatures) {
        return FeatureListResponse.newBuilder().addAllFeatures(
                smartFeatures.stream().map(DtoTransformer::buildFeatureResponse)
                        .collect(Collectors.toUnmodifiableList())
        ).build();
    }

    public static FeatureListResponse buildFeatureListResponse(SmartFeature smartFeature) {
        ensureNotNull(smartFeature, INVALID_SMART_FEATURE);
        return FeatureListResponse.newBuilder()
                .addFeatures(buildFeatureResponse(smartFeature)).build();
    }

    public static SmartFeature buildSmartFeature(FeatureRequest request) {
        ensureNotNull(request, INVALID_REQUEST);
        SmartFeature smartFeature = new SmartFeature();
        smartFeature.setName(request.getName());
        smartFeature.setModelType(request.getModelType());
        smartFeature.setCategory(request.getCategory().name());
        return smartFeature;
    }

    private static void ensureNotNull(Object obj, String message) {
        if (obj == null) {
            throw new GenericException(message);
        }
    }
}