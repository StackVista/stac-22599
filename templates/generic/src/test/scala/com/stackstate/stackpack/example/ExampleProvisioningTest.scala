package com.stackstate.stackpack.example

import com.stackstate.stackpack.testkit.TestKit
import org.scalatest.{Matchers, WordSpecLike}

import scala.collection.JavaConverters._

class ExampleProvisioningTest extends WordSpecLike with Matchers {
  val testKit = new TestKit()
  import testKit._

  val config = Map.empty[String, Object]

  "The Example Provisioning" should {
    "import a template" in {
      stackPackPackage.preInstall(provisioningContext, config.asJava).run()
      stackPackPackage.install(provisioningContext, config.asJava).run()

      val (_, templateFile, _) = verifyTemplateImportedWithNamespace(provisioningContext.stackPack.namespace)
      templateFile shouldBe "templates/stackpack.sty"
    }
  }
}
