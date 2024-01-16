// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.diffplug.spotless") version "6.22.0"
}

spotless {
    format("misc") {
        target("**/*.gradle", "**/*.md", "**/.gitignore")

        indentWithTabs()
        trimTrailingWhitespace()
        endWithNewline()
    }


    kotlin {
        ktfmt("0.46").kotlinlangStyle()

        target("**/*.kt")
        trimTrailingWhitespace()
        indentWithTabs()
        endWithNewline()
    }
}