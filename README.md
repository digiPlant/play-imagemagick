# play-imagemagick
Play plugin that works with play-res to rescale images with the help of image magick

# Get PGP key
https://github.com/xerial/sbt-sonatype


# Release snapshot version
```bash
sbt publishSigned
```

# Release production version to maven central
remove -SNAPSHOT from version
```bash
sbt sonatypeRelease
```