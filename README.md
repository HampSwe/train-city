# train-city

## About
This is a transit diagram of the fictional city "Train City". Every day, the members of "TÅGET" (a student group at LTH) vote on a new location to add. The new location is then, courtesy of the TågMeister, beautifully added to a vector image of the transit diagram. This repository aims to bring that vector image to life.

![alt text](https://github.com/HampSwe/train-city/blob/main/images/jpeg/latest.jpeg "Latest version of Train City")


## Execution
Clone the files to your machine. Go to the src/ directory and run
```
scala-cli run .
``` 

## Details
The project uses a library called [PixelWindow](https://fileadmin.cs.lth.se/pgk/api/api/introprog/PixelWindow.html). This is bad for two reasons:

<ol>
  <li>It defeats one of the core principles of transit diagrams: they should be in a vector format. PixelWindow, as the name suggests, does not support vector images.</li>
  <li>The library is very inefficient.</li>
</ol>

But, we use it anyways.

The project was inspired by https://www.reddit.com/r/TransitDiagrams/.

Feel free to contribute in any shape or form!
