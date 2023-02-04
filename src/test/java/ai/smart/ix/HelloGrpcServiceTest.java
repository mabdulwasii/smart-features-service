package ai.smart.ix;

import ai.smart.ix.repository.SmartFeatureRepository;
import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.Duration;

import static ai.smart.ix.utils.AppConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class HelloGrpcServiceTest {
    @GrpcClient
    MutinyFeatureGrpcGrpc.MutinyFeatureGrpcStub service;

    @Inject
    SmartFeatureRepository repository;

    FeatureRequest createRequest;
    GetFeatureRequest featureByValidId;
    GetFeatureRequest featureByInValidId;
    GetFeatureRequest featureByValidName;
    GetFeatureRequest featureByInValidName;
    GetFeatureRequest featureByValidType;
    GetFeatureRequest featureByInValidType;
    GetFeatureRequest featureByValidCategory;
    FeatureByModelTypeCategoryRequest featureByValidTypeCategory;
    GetFeatureRequest allFeatures;

    @BeforeEach
    public void setUp() {
        createRequest = FeatureRequest.newBuilder()
                .setName(VALID_NAME)
                .setModelType(VALID_MODEL_TYPE)
                .setCategory(VALID_CATEGORY)
                .build();

        allFeatures = GetFeatureRequest.newBuilder().build();
        featureByValidId = GetFeatureRequest.newBuilder().setId(VALID_ID).build();
        featureByInValidId = GetFeatureRequest.newBuilder().setId(INVALID_ID).build();
        featureByValidName = GetFeatureRequest.newBuilder().setName(VALID_NAME).build();
        featureByInValidName = GetFeatureRequest.newBuilder().setName(INVALID_NAME).build();
        featureByValidType = GetFeatureRequest.newBuilder().setModelType(VALID_MODEL_TYPE).build();
        featureByInValidType = GetFeatureRequest.newBuilder().setModelType(INVALID_MODEL_TYPE).build();
        featureByValidCategory = GetFeatureRequest.newBuilder().setCategory(VALID_CATEGORY).build();
        featureByValidTypeCategory = FeatureByModelTypeCategoryRequest.newBuilder()
                .setModelType(VALID_MODEL_TYPE).setCategory(VALID_CATEGORY).build();
    }

    @Test
    @DisplayName(SHOULD_CREATE_FEATURE)
    void shouldCreateFeature() {
        FeatureResponse response = service.createFeature(createRequest).await().atMost(Duration.ofSeconds(5));
        assertValidResponse(response);
    }

    @Test
    @DisplayName(GET_FEATURE_WITH_VALID_ID)
    void shouldGetFeatureByValidId() {
        FeatureListResponse response = service.getFeatures(featureByValidId)
                .await().atMost(Duration.ofSeconds(20));
        assertValidResponse(response);
    }

    @Test
    @DisplayName(THROW_ERROR_WITH_INVALID_ID)
    void shouldThrowToGetFeatureByInValidId() {
        Throwable th = assertThrows(Exception.class,
                () -> service.getFeatures(featureByInValidId)
                .await().atMost(Duration.ofSeconds(20)));

        assertTrue(th.getMessage().contains(INVALID_SMART_FEATURE));
    }

    @Test
    @DisplayName(GET_FEATURE_WITH_VALID_NAME)
    void shouldGetFeatureByValidName() {
        FeatureListResponse response = service.getFeatures(featureByValidName)
                .await().atMost(Duration.ofSeconds(20));
        assertValidResponse(response);
    }

    @Test
    @DisplayName(FAIL_FEATURE_WITH_INVALID_NAME)
    void shouldFailToGetFeatureByInValidName() {
        Throwable th = assertThrows(Exception.class,
                () -> service.getFeatures(featureByInValidName)
                        .await().atMost(Duration.ofSeconds(20)));

        assertTrue(th.getMessage().contains(INVALID_SMART_FEATURE));
    }

    @Test
    @DisplayName(GET_FEATURE_WITH_VALID_MODEL_TYPE)
    void shouldGetFeatureByValidModelType() {
        FeatureListResponse response = service.getFeatures(featureByValidType)
                .await().atMost(Duration.ofSeconds(20));
        Long count = (long) repository.findByModelType(VALID_MODEL_TYPE).await().atMost(Duration.ofSeconds(5)).size();
        assertEquals(count, response.getFeaturesList().size());
    }

    @Test
    @DisplayName(FAIL_FEATURE_WITH_INVALID_MODEL_TYPE)
    void shouldFailToGetFeatureByInValidModelType() {
        FeatureListResponse response = service.getFeatures(featureByInValidType)
                .await().atMost(Duration.ofSeconds(20));

        assertInvalidResponse(response);
    }

    @Test
    @DisplayName(GET_FEATURE_WITH_VALID_CATEGORY)
    void shouldGetFeatureByValidCategory() {
        FeatureListResponse response = service.getFeatures(featureByValidCategory)
                .await().atMost(Duration.ofSeconds(20));

        Long count = (long) repository.findByCategory(VALID_CATEGORY).await().atMost(Duration.ofSeconds(5)).size();
        assertEquals(count, response.getFeaturesList().size());
    }

    @Test
    @DisplayName(GET_ALL_FEATURES)
    void shouldGetAllFeatures() {
        FeatureListResponse response = assertDoesNotThrow(() ->
                service.getFeatures(allFeatures)
                        .await().atMost(Duration.ofSeconds(20))
        );
        Long count = repository.count().await().atMost(Duration.ofSeconds(5));
        assertEquals(count, response.getFeaturesList().size());
    }

    @Test
    @DisplayName(GET_FEATURE_WITH_VALID_MODEL_TYPE_CATEGORY)
    void shouldGetFeatureByValidModelTypeCategory() {
        FeatureListResponse response = service.getFeatureByModelTypeAndCategory(featureByValidTypeCategory)
                .await().atMost(Duration.ofSeconds(20));

        Long count = (long) repository.findByModelTypeAndCategory(VALID_MODEL_TYPE, VALID_CATEGORY)
                .await().atMost(Duration.ofSeconds(5)).size();
        assertEquals(count, response.getFeaturesList().size());
    }

    private static void assertValidResponse(FeatureListResponse responseList) {
        assertTrue(responseList.getFeaturesList().size() > 0);
        FeatureResponse response = responseList.getFeatures(0);
        assertNotNull(response);
        assertEquals(VALID_NAME, response.getName());
        assertEquals(VALID_MODEL_TYPE, response.getModelType());
        assertEquals(VALID_CATEGORY, response.getCategory());
    }

    private static void assertInvalidResponse(FeatureListResponse responseList) {
        assertEquals(0, responseList.getFeaturesList().size());
    }

    private void assertValidResponse(FeatureResponse response) {
        assertNotNull(response);
        assertEquals(VALID_NAME, response.getName());
        assertEquals(VALID_MODEL_TYPE, response.getModelType());
        assertEquals(VALID_CATEGORY, response.getCategory());
    }
}