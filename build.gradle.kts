// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    val compose_version by extra("1.1.0-beta01")
    repositories{
        google()
        mavenCentral()

    }
    dependencies{
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}