package se.digiplant.imagemagick.plugin.api

import play.api._
import java.io.{File, FileInputStream, OutputStream}

import org.apache.commons.io.{FilenameUtils, IOUtils}

import org.im4java.core.{ConvertCmd, IMOperation}

import util.Random
import se.digiplant.res.api.Res
import play.api.Play.current

object IMPlugin {

  lazy val configuration = Play.configuration.getConfig("imagemagick").getOrElse(Configuration.empty)

  /**
   * Creates and caches image in local cache directory
    *
    * @param path The path to where the images are stored
   * @param file The filePath relative to the path variable (the same as the play Assets Controller)
   * @return a File if everything when well
   */
  def get(path: String, file: String, setting: String): Option[File] = {

    val resourceName = Option(path + "/" + file).map(name => if (name.startsWith("/")) name else ("/" + name)).get
    val resourceFile = Play.getFile(resourceName)

    if (resourceFile.isDirectory) {
      None
    } else {
      val cachePath = configuration.getString("cachedir").getOrElse("tmp/imagemagickcache")
      val cachedImage = Res.fileWithMeta(
        filePath = FilenameUtils.concat(cachePath, file),
        meta = Seq(setting)
      )

      cachedImage.map { cachedImage =>
        Some(cachedImage)
      }.getOrElse {
        val resizedImage = resize(resourceFile, IMPluginSettings.get(setting))
        val resizedFilePath = Res.saveWithMeta(
          resizedImage,
          filePath = FilenameUtils.concat(cachePath, file),
          meta = Seq(setting)
        )
        Play.getExistingFile(resizedFilePath)
      }
    }
  }

  /**
   *
   * @param fileuid The unique play-res file identifier
   * @param source The play-res source name
   * @return a File if everything when well
   */
  def getRes(fileuid: String, source: String = "default", setting: String = "default"): Option[File] = {

    Res.get(fileuid, source).flatMap { res =>

      val cacheSource = configuration.getString("cache").getOrElse("imagemagickcache")

      val cachedImage = Res.get(
        fileuid = res.getName,
        meta = Seq(setting),
        source = cacheSource
      )

      cachedImage.map { cachedImage =>
        Some(cachedImage)
      }.getOrElse {
        val resizedImage = resize(res, IMPluginSettings.get(setting))
        val fileUID = Res.put(
          resizedImage,
          cacheSource,
          filename = Some(res.getName),
          meta = Seq(setting)
        )
        Res.get(fileUID, cacheSource)
      }
    }
  }

  /**
   * Resizes an image and stores it in a temp file that we then can move into a cache
   *
   * @param file The file to resize
   * @return A resized file
   */
  def resize(file: File, op: IMOperation): File = {
    // create temp file
    val ext = guessImageFormat(file)
    val tmp = File.createTempFile(Random.nextString(20), ext)

    // create convert command
    val cmd = new ConvertCmd()

    // execute the operation
    cmd.run(op, file.getPath, tmp.getPath);
    tmp
  }

  /**
   * Detect the image format "jpg" or "png"
   * Done by reading the first bytes of the file to check if it correspond to a file format "signature"
    *
    * @see http://www.garykessler.net/library/file_sigs.html
   * @param file The File we want to analyse
   * @return A String representing the image format "jpg" or "png"
   */
  def guessImageFormat(file: File): String = {
    val is = new FileInputStream(file)
    val firstBytes = new Array[Byte](2)
    is.read(firstBytes)
    is.close
    // A JPEG file always start with bytes FF D8
    if (firstBytes(0) == 0xFF.asInstanceOf[Byte] && firstBytes(1) == 0xD8.asInstanceOf[Byte])
      "jpg"
    else
      "png"
  }
}
