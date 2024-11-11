package suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import tests.VideoGamesTest;

@Suite
@SuiteDisplayName("Video Games API Test Suite")
@SelectClasses({VideoGamesTest.class})
public class VideoGamesTestSuite {
}
