package com.lightbend.kafkalagexporter

import java.util.concurrent.TimeUnit

import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.duration.FiniteDuration

object AppConfig {
  def apply(config: Config): AppConfig = {
    val pollIntervalConfig = config.getDuration("poll-interval")
    val pollInterval = FiniteDuration(pollIntervalConfig.toMillis, TimeUnit.MILLISECONDS)
    val bootstrapBrokers = config.getString("bootstrap-brokers")
    val port = config.getInt("port")
    val clientGroupId = config.getString("client-group-id")
    AppConfig(pollInterval, bootstrapBrokers, port, clientGroupId)
  }
}
case class AppConfig(pollInterval: FiniteDuration, bootstrapBrokers: String, port: Int, clientGroupId: String)
