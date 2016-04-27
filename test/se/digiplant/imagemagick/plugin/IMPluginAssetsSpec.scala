package se.digiplant.imagemagick.plugin

import org.specs2.mutable.Specification
import play.api.test._
import play.api.test.Helpers._

object IMPluginAssetsSpec extends Specification {

  "IMPlugin Controller" should {

    "return resized image in subfolder" in new ScalrContext {
      val result = IMPluginAssets.at("/test/resources", "subdir/test.jpg", "medium")(FakeRequest())
      status(result) must equalTo(OK)
      contentType(result) must beSome("image/jpeg")
    }

  }
}
