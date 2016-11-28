# scala-course
Scala course materials

## Preparation
1. Make sure you have Java Runtime Environment 1.8 or newer installed (https://www.java.com/en/download/)
2. Download SBT (sbt-extras is recommended)
    ```bash
    curl -L -o sbt https://raw.githubusercontent.com/paulp/sbt-extras/master/sbt && chmod +x sbt
    sudo ln -s $(pwd)/sbt /usr/local/bin/sbt
    ```

3. (Optional) Download Ammonite REPL

    ```bash
    curl -L -o amm https://git.io/vP4Gw && chmod +x amm
    sudo ln -s $(pwd)/amm /usr/local/bin/amm
    ```

4. (Optional) Install IntelliJ IDEA Community Edition (https://www.jetbrains.com/idea/download)
5. Clone this repository

    ```bash
    git clone 'https://github.com/michalsenkyr/scala-course.git'
    ```

## Viewing the slides
* Slides are available in [Markdown](slides/src/main/resources/web/slides.en.md) ([Czech version](slides/src/main/resources/web/slides.cs.md))
* A [Spray](http://spray.io/)/[Reveal.js](http://lab.hakim.se/reveal-js/) subproject is provided for presentation
    * Serving the presentation on port 8000: `sbt slides/run`
    * Serving the presentation on a different port: `sbt "slides/run 8080"`

## Executing examples using SBT
1. Run the `sbt` command in the scala-course directory
2. Switch to the appropriate subproject (eg. `project variables`)
3. Run the example: `run`
    * SBT will automatically find all the runnable classes and gives you a choice of which to run
    * For the duplicateFinder subproject, you will need to specify directories to find duplicates in, paths can be either absolute or relative to the duplicateFinder subproject directory: `run test`

## Trying out code using the Ammonite REPL
Run the `amm` command in any directory

## Subprojects
* slides - Contains slides and a Spray/Reveal.js project to present them
* variables - Contains examples of defining and using variables (slide 10)
* classes - Contains examples of defining classes/objects and their members (slides 11-18)
* patternMatching - Contains examples of pattern matching (slides 20-21)
* collections - Contains examples of constructing and using collections (slides 22-29)
* forComprehensions - Contains examples of for-comprehensions (slide 30)
* implicits - Contains examples of implicit classes (slide 31)
* duplicateFinder - Contains the solutions for the duplicate file finder exercise (slide 36)
    * Solutions include Scala with and without Java 8 as well as pure Java (with and without Java 8) for comparison
* tuplesGame - Contains the solution for the Tuples game exercise (slide 37)