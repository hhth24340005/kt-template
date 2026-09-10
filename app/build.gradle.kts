import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
  alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
  explicitApi()
  compilerOptions.freeCompilerArgs.addAll(
    "-Xexplicit-context-parameters",
    "-Xreturn-value-checker=full",
    "-Xcollection-literals",
  )

  @OptIn(ExperimentalKotlinGradlePluginApi::class)
  jvm {
    val main = "MainKt"
    binaries {
      executable {
        mainClass = main
      }
    }
    mainRun {
      mainClass = main
    }
  }

  sourceSets {
    commonMain {
      dependencies {
        implementation(project(":libs"))
        implementation(libs.kotlin.logging)
      }
    }

    jvmMain {
      dependencies {
        runtimeOnly(libs.logback.classic)
      }
    }
  }
}
