# Dominant Colors Analyzer

## Project Description

This project is an Android application that captures a live camera feed and analyzes it in real-time to display the five most dominant colors along with their respective percentages. The app continuously updates the display as the camera captures new frames.

### Key Features

- **Live Camera Feed Integration**: Utilizes Android's CameraX library for capturing live camera feed.
- **Real-Time Image Processing**: Scales down the captured image for faster processing and uses multithreading to divide and process the image concurrently.
- **Dominant Color Extraction**: Identifies the most dominant colors, counts color occurrences using a bucketing technique, and combines results from different sections of the image.
- **Dynamic UI Updates**: Displays the top five dominant colors with their percentages, using custom binding adapters to format the percentage text and adjust text color based on background color for better readability.

### Technical Highlights

- **Programming Language**: Java
- **Android SDK**: Utilized for app development and CameraX integration
- **Image Processing**: Custom utility classes for scaling and color bucketing
- **Multithreading**: Java's ExecutorService for concurrent image processing tasks
- **Data Binding**: Custom data binding adapters for formatting and displaying data

This project demonstrates the integration of real-time image processing with an Android application's UI, showcasing efficient use of threading and custom data binding to provide a seamless user experience.

### Download

You can download it from the Google Play Store:

[Download Dominant Colors Analyzer](https://play.google.com/store/apps/details?id=com.silverhorse.topfivecolors)

