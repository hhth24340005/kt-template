rootProject.name = TODO("Set the project name before building this project.")

pluginManagement {
  includeBuild("build-logic")

  repositories {
    gradlePluginPortal()
    google()
  }
}

include(":app")
