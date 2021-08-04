import com.github.gradle.node.task.NodeTask;
import com.github.gradle.node.npm.task.NpmTask;

plugins {
    base
    id("com.github.node-gradle.node") version "3.1.0"
}

node {
    download.set(true)
    version.set("14.17.4")
    npmVersion.set("7.20.3")
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

val prettier = task<NpmTask>("prettier") {
    description = "Runs Prettier."
    dependsOn(tasks.npmInstall)
    args.set(listOf("run", "prettier"))
}

val parallelRun = task<NodeTask>("parallelRun") {
    group = "application"
    description = "Runs React and Spring in parallel."
    dependsOn(tasks.npmInstall)
    script.set(file("server.js"))
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
