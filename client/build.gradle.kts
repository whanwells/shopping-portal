import com.github.gradle.node.npm.task.NpmTask;

plugins {
    base
    id("com.github.node-gradle.node") version "3.1.0"
}

tasks.npmInstall {
    inputs.file("package.json")
}

val reactScriptsBuild = task<NpmTask>("reactScriptsBuild") {
    group = "build"
    description = "Builds the client for production."
    dependsOn(tasks.npmInstall)
    environment.set(mapOf("CI" to "true"))
    args.set(listOf("run", "build"))
}

val reactScriptsStart = task<NpmTask>("reactScriptsStart") {
    group = "application"
    description = "Runs the client in development mode."
    dependsOn(tasks.npmInstall)
    args.set(listOf("start"))
}

val reactScriptsTest = task<NpmTask>("reactScriptsTest") {
    group = "verification"
    description = "Runs the unit tests."
    dependsOn(tasks.npmInstall)
    environment.set(mapOf("CI" to "true"))
    args.set(listOf("test"))
}

tasks.assemble {
    dependsOn(reactScriptsBuild)
}

tasks.check {
    dependsOn(reactScriptsTest)
}

tasks.clean {
    delete("build")
}
