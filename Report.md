# 2nd Assignment Report

## Introduction
In this assignment we should connect to a weather API and get the current weather information of a city. This information includes temperature, humidity, wind speed and direction. This information can be obtained from the weather data as a Json object.

## Design and Implementation
First I forked the repository to my git and cloned the project to my local system. Considering the data from the weather API is in Json data format, I added the library to the gradle file (build.gradle). In the main program, we get the user input for the city. Input will be taken until the user enters a valid city name. In the getWeatherData function, data is returned in the string format. We convert this string to a Json object. Then the "current" Json object is extracted from the whole data. This "current" data holds all the required aspects of the weather, so for every feature a part of it is used. For example for the temperature we get the value of the "temp_c" key in double. At the end I used java swing to visualize all of this. In swing, first we create a frame and labels that contain our text. Then I set the font, color and size of these labels. Now it's time to create a panel to which all the labels are added. The panel is added to the frame. This function works like a constructor and is called inside the main function.

## Testing and Evaluation
I tested several cities and compared the outputs to the data in the site. They were accurate. The only problem was when the input was not found and the program returned an error. To solve this, we ask for input until it's valid and found in the database.

## Conclusion
Json data is very useful when working with data input from sites. It can also hold different data types such as a string, integer, Json object etc. Also, Swing is a GUI toolkit for Java that is used to visualize data and create window-based applications. These two were the main tools used in this solution.