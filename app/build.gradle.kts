import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.ksp)
  alias(libs.plugins.kotest)
}

kotlin {
  explicitApi()
  compilerOptions.freeCompilerArgs.addAll(
    "-Xexplicit-context-arguments",
    "-Xreturn-value-checker=full",
    "-Xcollection-literals",
    "-Xname-based-destructuring=complete",
    "-Xcompanion-blocks-and-extensions",
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

    commonTest {
      dependencies {
        implementation(libs.bundles.kotest)
      }
    }

    jvmTest {
      dependencies {
        implementation(libs.kotest.runner.junit5)
      }
    }

    jvmMain {
      dependencies {
        runtimeOnly(libs.logback.classic)
      }
    }
  }
}

tasks.withType<Test>().configureEach {
  useJUnitPlatform()
}

tasks.withType<AbstractTestTask>().configureEach {
  testLogging {
    events("passed", "skipped", "failed")
    exceptionFormat = TestExceptionFormat.FULL
    showStandardStreams = true
    showStackTraces = false
  }
}
