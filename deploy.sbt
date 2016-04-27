import xerial.sbt.Sonatype.SonatypeKeys._

sonatypeSettings

// Workaround for bouncycastle not working with play 2.3

useGpg := true

//credentials := Seq(Credentials(Path.userHome / ".ivy2" / ".digiplantcredentials"))

licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

homepage := Some(url("https://github.com/digiPlant/play-imagemagick"))

pomExtra :=
    <scm>
      <url>git://github.com/digiPlant/play-imagemagick.git</url>
      <connection>scm:git://github.com/digiPlant/play-imagemagick.git</connection>
    </scm>
    <developers>
      <developer>
        <id>stefanjurmu</id>
        <name>Stefan Jurmu</name>
        <url>http://github.com/stefanjurmu</url>
      </developer>
    </developers>