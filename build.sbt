import com.peknight.build.gav.*
import com.peknight.build.sbt.*

commonSettings

lazy val financial = (project in file("."))
  .settings(name := "financial")
  .aggregate(
    financialCore.jvm,
    financialCore.js,
    financialStock,
    financialFund,
  )

lazy val financialCore = (crossProject(JVMPlatform, JSPlatform) in file("financial-core"))
  .settings(name := "financial-core")
  .settings(crossDependencies(
    peknight.codec.squants,
  ))

lazy val financialStock = (project in file("financial-stock"))
  .settings(name := "financial-stock")
  .aggregate(
    financialStockCore.jvm,
    financialStockCore.js,
  )

lazy val financialStockCore = (crossProject(JVMPlatform, JSPlatform) in file("financial-stock/core"))
  .dependsOn(financialCore)
  .settings(name := "financial-stock-core")
  .settings(crossDependencies(
  ))

lazy val financialFund = (project in file("financial-fund"))
  .settings(name := "financial-fund")
  .aggregate(
    financialFundCore.jvm,
    financialFundCore.js,
  )

lazy val financialFundCore = (crossProject(JVMPlatform, JSPlatform) in file("financial-fund/core"))
  .dependsOn(financialCore)
  .settings(name := "financial-fund-core")
  .settings(crossDependencies(
  ))
