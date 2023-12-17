//import org.junit.jupiter.api.Test;
//
//import java.util.NoSuchElementException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class BackendDeveloperTests {
//
//    /**
//     * Tests if findShortestpath() works
//     * @return true if and only if expected value is returned by the method
//     */
//    @Test
//    public void test1(){
//        String[] array = new String[]{"Miami", "Madison"};
//        ICityBackend backend = new CityBackend(new CityGraphBackendDeveloperPlaceholder(), new CityValidatorBackendPlaceholder(), array);
//        assertEquals(backend.findShortestpath("Madison", "Chicago"), 1);
//    }
//
//    /**
//     * Tests if listallcities() works
//     * @return true if and only if expected value is returned by the method
//     */
//    @Test
//    public void test2(){
//        String[] array = new String[]{"Miami", "Madison"};
//        ICityBackend backend = new CityBackend(new CityGraphBackendDeveloperPlaceholder(), new CityValidatorBackendPlaceholder(), array);
//        assertEquals(backend.listallcities(), array);
//    }
//
//    /**
//     * Test if exception is thrown if Origin City is not validated
//     * @return true if exception is thrown
//     */
//    @Test
//    public void test3(){
//        String[] array = new String[]{"Miami", "Madison"};
//        ICityBackend backend = new CityBackend(new CityGraphBackendDeveloperPlaceholder(), new CityValidatorBackendPlaceholder(), array);
//        assertThrows(NoSuchElementException.class, () -> {
//            backend.findShortestpath("Miami", "Madison");
//        });
//    }
//
//    /**
//     * Test if exception is thrown if Destination City is not validated
//     * @return true if exception is thrown
//     */
//    @Test
//    public void test4(){
//        String[] array = new String[]{"Miami", "Madison"};
//        ICityBackend backend = new CityBackend(new CityGraphBackendDeveloperPlaceholder(), new CityValidatorBackendPlaceholder(), array);
//        assertThrows(NoSuchElementException.class, () -> {
//            backend.findShortestpath("Madison", "Miami");
//        });
//    }
//
//    /**
//     * Tests if exception is thrown when size of array is zero
//     * @return true if exception is thrown
//     */
//    @Test
//    public void test5(){
//        String[] array = new String[]{};
//        assertThrows(IllegalArgumentException.class, () -> {
//            new CityBackend(new CityGraphBackendDeveloperPlaceholder(), new CityValidatorBackendPlaceholder(), array);;
//        });
//    }
//}
