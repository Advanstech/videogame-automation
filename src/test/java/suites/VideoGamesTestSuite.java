package suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import tests.VideoGamesTest;

/**
 * This test suite is designed to run all tests related to the Video Games API.
 * It includes tests for CRUD operations and ensures that the API behaves as expected.
 * The suite uses JUnit's @SelectClasses to specify the test classes to be executed.
 */
@Suite
@SuiteDisplayName("Video Games API Test Suite")
@SelectClasses({VideoGamesTest.class})
public class VideoGamesTestSuite {
    // This class remains empty. It is used only as a holder for the above annotations.
}