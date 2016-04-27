package se.digiplant.imagemagick.plugin

import org.specs2.mutable.Specification
import play.api.test._
import play.api.test.Helpers._
import se.digiplant.res.api.Res

object IMPluginResAssetsSpec extends Specification {

  "IMPlugin Res Assets Controller" should {

    "return resized image" in new ScalrContext {
      Res.put(testFile)
      val result = IMPluginResAssets.at("5564ac5e3968e77b4022f55a23d36630bdeb0274.jpg", "medium")(FakeRequest())
      status(result) must equalTo(OK)
      contentType(result) must beSome("image/jpeg")
    }
  }
}
