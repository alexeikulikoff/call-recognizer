/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package call.recognizer;

import org.junit.Test;

import com.mibs.recoginizer.App;

import static org.junit.Assert.*;

public class AppTest {
    @Test public void testAppHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}
