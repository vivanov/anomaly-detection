  lazy val commonSettings = Seq(
    organization := "ru.lester",
    version := "0.1.0",
    scalaVersion := "2.10.5",
    resolvers ++= Seq(
      "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases/",
      "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
      "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
      "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
      "Bintray sbt plugin releases" at "http://dl.bintray.com/sbt/sbt-plugin-releases/"
    ),
    scalacOptions ++= Seq(
      "-language:postfixOps",
      "-language:implicitConversions",
      "-language:reflectiveCalls",
      "-language:higherKinds",
      "-language:existentials",
      "-Yinline-warnings",
      "-Xlint",
      "-deprecation",
      "-feature",
      "-unchecked"
    )
  )

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "root"
  ).aggregate(example)

lazy val example = (project in file("example")).
  settings(commonSettings: _*).
  settings(
    name := "example",
    mainClass in assembly := Some("ru.lester.spark.example.anomaly.MultivariateGaussianDistribution"),
    assemblyMergeStrategy in assembly := {
        case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
        case PathList("javax", "transaction", xs @ _*)     => MergeStrategy.first
        case PathList("javax", "mail", xs @ _*)            => MergeStrategy.first
        case PathList("javax", "activation", xs @ _*)      => MergeStrategy.first
        case PathList("org", "apache", "spark", "unused", xs @ _*)      => MergeStrategy.discard
        case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
        case "application.conf" => MergeStrategy.concat
        case "unwanted.txt"     => MergeStrategy.discard
        case x if x.startsWith("META-INF/mailcap") => MergeStrategy.last
        case x => {
          val oldStrategy = (assemblyMergeStrategy in assembly).value
          oldStrategy(x)
        }
    },
    libraryDependencies ++= Seq(
      "org.scalanlp" %% "breeze" % "0.11.2",
      "org.scalanlp" %% "breeze-natives" % "0.11.2",
      ("org.apache.spark"   %% "spark-core"      % "1.3.0" % "provided").
        exclude("org.eclipse.jetty.orbit", "javax.servlet").
        exclude("org.eclipse.jetty.orbit", "javax.transaction").
        exclude("org.eclipse.jetty.orbit", "javax.mail").
        exclude("org.eclipse.jetty.orbit", "javax.activation").
        exclude("org.apache.hadoop", "hadoop-yarn-common").
        exclude("org.apache.hadoop", "hadoop-yarn-api").
        exclude("commons-beanutils", "commons-beanutils-core").
        exclude("commons-collections", "commons-collections").
        exclude("commons-logging", "commons-logging").
        exclude("com.esotericsoftware.minlog", "minlog").
        exclude("org.slf4j", "slf4j-log4j12").
        exclude("com.google.guava", "guava"),
      "org.apache.spark" % "spark-streaming_2.10" % "1.3.0" % "provided",
      "org.apache.spark" % "spark-mllib_2.10" % "1.3.0" % "provided"
    )
  )



