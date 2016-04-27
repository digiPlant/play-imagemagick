// Your profile name of the sonatype account. The default is the same with the organization value
sonatypeProfileName := "se.digiplant"

// To sync with Maven central, you need to supply the following information:
pomExtra in Global := {
  <url>https://github.com/digiPlant/play-imagemagick</url>
  <licenses>
    <license>
      <name>MIT</name>
      <url>https://en.wikipedia.org/wiki/MIT_License</url>
    </license>
  </licenses>
  <scm>
    <connection>scm:git://github.com/digiPlant/play-imagemagick.git</connection>
    <developerConnection>scm:git:git@github.com:digiPlant/play-imagemagick</developerConnection>
    <url>github.com/digiPlant/play-imagemagick</url>
  </scm>
  <developers>
    <developer>
      <id>stefanjurmu</id>
      <name>Stefan Jurmu</name>
      <url>https://github.com/stefanjurmu</url>
    </developer>
  </developers>
}