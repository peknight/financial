import com.peknight.build.gav.*
import com.peknight.build.sbt.*

commonSettings

lazy val financial = (project in file("."))
  .settings(name := "financial")
  .aggregate(financialCore.projectRefs *)
  .aggregate(financialStock.projectRefs *)
  .aggregate(financialFund.projectRefs *)

lazy val financialCore = (projectMatrix in file("financial-core"))
  .settings(name := "financial-core")
  .settings(libraryDependencies ++= dependencies(
    peknight.codec.squants,
  ))
  .jvmPlatform(scalaVersions = Seq(scala.scala3.version))
  .jsPlatform(scalaVersions = Seq(scala.scala3.version))

lazy val financialStock = (projectMatrix in file("financial-stock/core"))
  .dependsOn(financialCore)
  .settings(name := "financial-stock-core")
  .jvmPlatform(scalaVersions = Seq(scala.scala3.version))
  .jsPlatform(scalaVersions = Seq(scala.scala3.version))

lazy val financialFund = (projectMatrix in file("financial-fund/core"))
  .dependsOn(financialCore)
  .settings(name := "financial-fund-core")
  .jvmPlatform(scalaVersions = Seq(scala.scala3.version))
  .jsPlatform(scalaVersions = Seq(scala.scala3.version))
