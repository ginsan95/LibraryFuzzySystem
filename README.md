# LibraryFuzzySystem
## Introduction
This project is a libarry coldness fuzzy control system, where based on input variables such as the weather and air-con temperature, the fuzzy system will calculate the coldness level felt by the students.

## Methodology
### Membership function
I used the triangular and trapezoidal membership functions. There are 2 input variables which are weather and air-con temperature, and 1 output variable which is coldness level. Their respective fit vectors are as following:
![Weather graph](https://github.com/ginsan95/LibraryFuzzySystem/blob/master/demo/doc/weather%20graph.jpg?raw=true)
![Air-con temperature graph](https://github.com/ginsan95/LibraryFuzzySystem/blob/master/demo/doc/air%20con%20graph.jpg?raw=true)
![Coldness level graph](https://github.com/ginsan95/LibraryFuzzySystem/blob/master/demo/doc/coldness%20graph.jpg?raw=true)

### Rules
* RULE 1 – IF weather IS sunny AND air-con temperature IS high, THEN coldness level IS hot
*	RULE 2 – IF weather IS sunny AND air-con temperature IS average, THEN coldness level IS average
*	RULE 3 – IF weather IS cloudy AND air-con temperature IS high, THEN coldness level IS average
*	RULE 4 – IF weather IS cloudy AND air-con temperature IS average, THEN coldness level IS average
*	RULE 5 – IF weather IS raining OR air-con temperature IS low, THEN coldness level IS cold

### Defuzzification
For the defuzzifaction process, I used the center of sums technique.

## Screenshots
![Screenshot 1](https://github.com/ginsan95/LibraryFuzzySystem/blob/master/demo/screenshots/screenshot%201.jpg?raw=true)
![Screenshot 2](https://github.com/ginsan95/LibraryFuzzySystem/blob/master/demo/screenshots/screenshot%202.jpg?raw=true)
