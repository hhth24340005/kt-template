import org.gradle.api.tasks.testing.logging.TestExceptionFormat

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
  )
  jvm()

  sourceSets {
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
