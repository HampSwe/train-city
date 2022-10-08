# train-city
This is a transit diagram of the fictional city "Train City". Daily, the members of "tåget" (a student group at LTH) vote on a new location to add. The new location is then, courtesy of the TågMeister, beautifully added to a vector image of the transit diagram. This repository aims to bring that vector image to life.

![alt text](https://github.com/HampSwe/train-city/tree/main/images/07-10-22 "Latest version of Train City")

The project uses a library called "PixelWindow". This is bad for two reasons:

(1) It defeats one of the core principles of transit diagrams: they should be in a vector format. PixelWindow, as the name suggests, does not support vector images.
(2) The library is very inefficient.

But, we use it anyways.

The project was inspired by https://www.reddit.com/r/TransitDiagrams/.

Feel free to contribute in any shape or form.
