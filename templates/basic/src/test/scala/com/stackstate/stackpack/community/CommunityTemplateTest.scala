package com.stackstate.stackpack.community

import com.stackstate.domain.handlebars.ElementDependency._
import com.stackstate.domain.handlebars.HandlebarsTemplateRunner
import com.stackstate.exportimport.SerializationHelpers
import org.scalatest.{Matchers, WordSpecLike}

import scala.collection.JavaConverters._
import scala.language.existentials

class CommunityTemplateTest extends WordSpecLike with Matchers {
  "The community-kubernetes.sty template should be valid" in {
    val config: Map[String, Object] = Map[String, Object]("stj.includeRootDirectory" -> "src/main/stackpack/provisioning")
    val runner =
      HandlebarsTemplateRunner.fromFile(templateName = "provisioning/templates/community-kubernetes", escapeResolveHelpers = true, fileExtension = ".sty")
    val resolvedData = runner.RunTemplate(
      config.asJava,
      List(MonitorFunctionDependency(identifier = Some("urn:stackpack:common:monitor-function:topological-threshold"))),
    )
    SerializationHelpers.readAsSprayJSON(resolvedData)
  }
}
