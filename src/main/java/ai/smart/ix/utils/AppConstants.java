package ai.smart.ix.utils;

import ai.smart.ix.FeatureCategory;

public class AppConstants {

    private AppConstants() {
    }

    public static final String INVALID_SMART_FEATURE = "Invalid smartFeature";
    public static final String INVALID_REQUEST = "Invalid request";

    //    Test Constants
    public static final String VALID_NAME = "Get Body Temperature";
    public static final String VALID_MODEL_TYPE = "Smart Watch";
    public static final FeatureCategory VALID_CATEGORY = FeatureCategory.DEVICE;
    public static final long VALID_ID = 1L;
    public static final long INVALID_ID = -1L;
    public static final String SHOULD_CREATE_FEATURE = "Should create feature";
    public static final String GET_FEATURE_WITH_VALID_ID = "Should get feature with valid id";
    public static final String GET_FEATURE_WITH_VALID_NAME = "Should get feature with valid name";
    public static final String GET_FEATURE_WITH_VALID_MODEL_TYPE = "Should get feature with valid model type ";
    public static final String GET_FEATURE_WITH_VALID_CATEGORY = "Should get feature with valid category";
    public static final String GET_FEATURE_WITH_VALID_MODEL_TYPE_CATEGORY = "Should get feature with valid type and category";
    public static final String GET_ALL_FEATURES = "Should get all features";
    public static final String FAIL_FEATURE_WITH_INVALID_NAME = "Should fail to get feature with invalid name";
    public static final String FAIL_FEATURE_WITH_INVALID_MODEL_TYPE = "Should fail to get feature with invalid model type";
    public static final String THROW_ERROR_WITH_INVALID_ID = "Should throw error with invalid id";
    public static final String INVALID_NAME = "Invalid Name";
    public static final String INVALID_MODEL_TYPE = "Invalid model";
}