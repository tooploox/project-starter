package com.project.starter.quality.internal

import org.gradle.api.Project
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

internal fun Project.configureKotlinCoverage() {
    pluginManager.apply("jacoco")

    tasks.named("jacocoTestReport", JacocoReport::class.java) {
        it.dependsOn(":$path:test")
        it.reports.apply {
            xml.isEnabled = true
            html.isEnabled = true
        }
    }
    tasks.named("test") { testTask ->
        testTask.extensions.getByType(JacocoTaskExtension::class.java).apply {
            excludes = dagger
        }
    }
}
