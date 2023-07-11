package moulton.thomas.weather_aggregator.datasource;

import moulton.thomas.weather_aggregator.translator.OpenMetroDataTranslator;
import moulton.thomas.weather_aggregator.model.WeatherData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test class for OpenMetaDataSource.
 * This class demonstrates how to test methods in OpenMetaDataSource using JUnit and Mockito for mocking dependencies.
 *
 * Author: Thomas Moulton
 */
public class OpenMetaDataSourceTest {
    
    // Declare the object under test (OpenMetaDataSource) and the dependencies that will be mocked (OpenMetroDataTranslator)
    private OpenMetaDataSource openMeteoDataSource;
    private OpenMetroDataTranslator dataTranslator;

    /**
     * Set up method for test environment.
     * This method is invoked before each test case.
     * It initialises the object under test and mocks its dependencies.
     */
    @BeforeEach
    public void setUp() {
        // Mock the OpenMetroDataTranslator dependency
        dataTranslator = mock(OpenMetroDataTranslator.class);

        // Initialise OpenMetaDataSource with the mock dependencies
        openMeteoDataSource = new OpenMetaDataSource(1.0, 1.0, dataTranslator);
    }

    /**
     * Test method for fetchData() in OpenMetaDataSource.
     * This test verifies that the fetchData() method fetches data correctly and the translator's method is called once.
     */
    @Test
    public void testFetchData() {
        // Prepare data for the mock to return when its method is called
        List<WeatherData> expectedWeatherDataList = new ArrayList<>();
        WeatherData weatherData = new WeatherData();
        expectedWeatherDataList.add(weatherData);
        
        // Configure the mock translator to return your prepared data when its translateData method is called
        when(dataTranslator.translateData(any(JsonObject.class))).thenReturn(expectedWeatherDataList);

        // Call the method under test
        List<WeatherData> actualWeatherDataList = openMeteoDataSource.fetchData();

        // Assert that the actual result matches the expected result
        assertEquals(expectedWeatherDataList, actualWeatherDataList, "The returned data should match the expected result.");

        // Verify that the translator's translateData method was called exactly once
        verify(dataTranslator, times(1)).translateData(any(JsonObject.class));
    }
}
