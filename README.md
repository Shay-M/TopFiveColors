**Project Description**
This project is an Android application that captures a live camera feed and analyzes it in real-time
to display the five most dominant colors along with their respective percentages. The app
continuously updates the display as the camera captures new frames. Key features include live camera
feed integration using Android's CameraX library, real-time image processing by scaling down the
captured image for faster processing, and utilizing multithreading to divide the image into sections
and process them concurrently for efficiency. The dominant color extraction feature analyzes the
captured image to identify the most dominant colors, counts color occurrences using a bucketing
technique to group similar colors, and combines results from different sections of the image. The
dynamic UI updates display the top five dominant colors with their percentages, using custom binding
adapters to format the percentage text and adjust text color based on background color for better
readability. The project is optimized for performance, implementing efficient algorithms to ensure
real-time performance and utilizing a fixed thread pool for concurrent image processing tasks. The
user-friendly interface provides a clear and simple display of dominant colors and their
percentages, ensuring text visibility with dynamic color adjustments based on background brightness.
Technical highlights include the use of Java, Android SDK, CameraX for image processing, custom
utility classes for scaling and color bucketing, Java's ExecutorService for multithreaded
processing, custom data binding adapters for formatting and displaying data, and TextView elements
with dynamically updated text and background colors. This project demonstrates the integration of
real-time image processing with an Android application's UI, showcasing efficient use of threading
and custom data binding to provide a seamless user experience.