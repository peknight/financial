import com.peknight.build.gav.*
import com.peknight.build.sbt.*

commonSettings

lazy val financial = (project in file("."))
  .settings(name := "financial")
  .aggregate(
    financialCore.jvm,
    financialCore.js,
  )

lazy val financialCore = (crossProject(JVMPlatform, JSPlatform) in file("financial-core"))
  .settings(name := "financial-core")
  .settings(crossDependencies(
      peknight.codec,
  ))
