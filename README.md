spark
=====

This repository contains example code for playing around with Apache Spark.
Each subdirectory has a number associated and demos a simple concept using
scala code.

For running this I am using the following simple trick:

1. Copy sbt/sbt, sbt/sbt-launch-0.13.1.jar and sbt/sbt-launch-lib.bash
   from the spark source tree into ~/bin/
2. In ~/bin/sbt-launch-lib.bash change the line
   `JAR=sbt/sbt-launch-${SBT_VERSION}.jar`
   to
   `JAR=~/bin/sbt-launch-${SBT_VERSION}.jar`
3. Go into a numbered directory and do `~/bin/sbt run`

Note: Without the project/build.properties, sbt tries to download the
sbt-launch\*.jar file.
