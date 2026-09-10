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
  jvm()
}
