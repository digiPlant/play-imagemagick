package se.digiplant.imagemagick.plugin.api

import org.specs2.mutable.Specification
import java.io.{File, FileInputStream}

import org.apache.commons.io.IOUtils
import javax.imageio.ImageIO

import org.im4java.core.IMOperation
import se.digiplant.imagemagick.plugin.ScalrContext
import se.digiplant.res.api.Res
import se.digiplant.scalr.api

object IMPluginSpec extends Specification {

  "IMPlugin Plugin" should {

    "resize image with default operations" in new ScalrContext {
      val fileuid = Res.put(testFile)
      fileuid.isEmpty must beFalse
      val file = new File("tmp/default/5564/ac5e/5564ac5e3968e77b4022f55a23d36630bdeb0274.jpg")
      file.exists() must beTrue

      val test = Res.get(fileuid).get

      val resizedSmall = IMPlugin.resize(test, IMPluginSettings.get("small_fit_to_width"))
      resizedSmall.exists() must beTrue

      val in1 = new FileInputStream(resizedSmall)
      val buf1 = ImageIO.read(in1)
      IOUtils.closeQuietly(in1)
      buf1.getWidth must beEqualTo(640)

      val resizedMedium = IMPlugin.resize(test, IMPluginSettings.get("medium_fit_to_height"))
      resizedMedium.exists() must beTrue

      val in2 = new FileInputStream(resizedMedium)
      val buf2 = ImageIO.read(in2)
      IOUtils.closeQuietly(in2)
      buf2.getHeight must beEqualTo(800)

      val resizedLarge = IMPlugin.resize(test, IMPluginSettings.get("large_fit_to_exact"))
      resizedLarge.exists() must beTrue

      val in3 = new FileInputStream(resizedLarge)
      val buf3 = ImageIO.read(in3)
      IOUtils.closeQuietly(in3)
      buf3.getWidth must beEqualTo(2560)
      buf3.getHeight must beEqualTo(1600)
    }

    "crop image with default operations" in new ScalrContext {
      val fileuid = Res.put(largeTestFile)
      fileuid.isEmpty must beFalse
      val file = new File("tmp/default/ebe5/b3ea/ebe5b3ea05c8faa0c3d1e71c0b113da8cac754a7.jpg")
      file.exists() must beTrue

      val test = Res.get(fileuid).get

      val croppedMedium = IMPlugin.resize(test, IMPluginSettings.get("medium_cropped"))
      croppedMedium.exists() must beTrue

      val in = new FileInputStream(croppedMedium)
      val buf = ImageIO.read(in)
      IOUtils.closeQuietly(in)
      buf.getWidth must beEqualTo(1280)
      buf.getHeight must beEqualTo(800)
    }

    "thumbnail image with default operations" in new ScalrContext {
      val fileuid = Res.put(testFile)
      fileuid.isEmpty must beFalse
      val file = new File("tmp/default/ebe5/b3ea/ebe5b3ea05c8faa0c3d1e71c0b113da8cac754a7.jpg")
      file.exists() must beTrue

      val test = Res.get(fileuid).get

      val thumbnailSmall = IMPlugin.resize(test, IMPluginSettings.get("small_thumbnail"))
      thumbnailSmall.exists() must beTrue

      val in = new FileInputStream(thumbnailSmall)
      val buf = ImageIO.read(in)
      IOUtils.closeQuietly(in)
      buf.getWidth must beEqualTo(50)
      buf.getHeight must beEqualTo(50)
    }

    "resize image with custom operations" in new ScalrContext {
      val fileuid = Res.put(testFile)
      fileuid.isEmpty must beFalse
      val file = new File("tmp/default/ebe5/b3ea/ebe5b3ea05c8faa0c3d1e71c0b113da8cac754a7.jpg")
      file.exists() must beTrue

      val test = Res.get(fileuid).get

      val op = new IMOperation()
      op.addImage()
      op.resize(500, 500);
      op.quality(90.0);
      op.addImage();

      IMPluginSettings.add("custom_small", op)
      val customSmall = IMPlugin.resize(test, IMPluginSettings.get("custom_small"))
      customSmall.exists() must beTrue

      val in1 = new FileInputStream(customSmall)
      val buf1 = ImageIO.read(in1)
      IOUtils.closeQuietly(in1)
      buf1.getWidth must beEqualTo(500)
      buf1.getHeight must beEqualTo(500)

      IMPluginSettings.add("custom_medium", IMPluginSettings.resize(1280, 800, '!', 80.0))
      val customMedium = IMPlugin.resize(test, IMPluginSettings.get("custom_medium"))
      customMedium.exists() must beTrue

      val in2 = new FileInputStream(customMedium)
      val buf2 = ImageIO.read(in2)
      IOUtils.closeQuietly(in2)
      buf2.getWidth must beEqualTo(1280)
      buf2.getHeight must beEqualTo(800)
    }

    "resize image with default setting" in new ScalrContext {
      val fileuid = Res.put(testFile)
      fileuid.isEmpty must beFalse
      val file = new File("tmp/default/ebe5/b3ea/ebe5b3ea05c8faa0c3d1e71c0b113da8cac754a7.jpg")
      file.exists() must beTrue

      val test = Res.get(fileuid).get

      val default1 = IMPlugin.resize(test, IMPluginSettings.get("default"))
      default1.exists() must beTrue

      val in1 = new FileInputStream(default1)
      val buf1 = ImageIO.read(in1)
      IOUtils.closeQuietly(in1)
      buf1.getWidth must beEqualTo(600)

      val default2 = IMPlugin.resize(test, IMPluginSettings.get("no_setting"))
      default2.exists() must beTrue

      val in2 = new FileInputStream(default2)
      val buf2 = ImageIO.read(in2)
      IOUtils.closeQuietly(in2)
      buf2.getWidth must beEqualTo(600)
    }

  }
}
