package controllers

import play.api.mvc.Controller
import models.Image
import se.digiplant.imagemagick.plugin.IMPluginResAssets

object Images extends Controller {
  // Default Source
  def at(fileuid: String, width: Int, height: Int) = IMPluginResAssets.at(fileuid, width, height, source="default")
  def crop(fileuid: String, width: Int, height: Int) = IMPluginResAssets.at(fileuid, width, height, mode = "crop", source="default")
  def thumb(fileuid: String) = IMPluginResAssets.at(fileuid, 120, 120, mode = "crop", source="default")

  // ProfileImage Source
  def profile(fileuid: String) = IMPluginResAssets.at(fileuid, 120, 120, mode = "crop", source="profileimage")
}
