rootProject.name = TODO("Set the project name before building this project.")

pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
  }
}

dependencyResolutionManagement {
  @Suppress("UnstableApiUsage")
  repositories {
    mavenCentral()
  }
}

include(":app")
