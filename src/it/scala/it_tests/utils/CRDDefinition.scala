package it_tests.utils

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import io.fabric8.kubernetes.api.model._
import io.fabric8.kubernetes.client.CustomResource

case class CustomObject @JsonCreator() (@JsonProperty("spec") spec: SparkApplicationSpec,
                                        @JsonProperty("status") status: Option[Status]) extends CustomResource

case class Driver @JsonCreator() (@JsonProperty("cores") cores: Double,
                                  @JsonProperty("coreLimit") coreLimit: String,
                                  @JsonProperty("memory") memory: String,
                                  @JsonProperty("serviceAccount") serviceAccount: String,
                                  @JsonProperty("labels") labels: Labels)

case class Executor @JsonCreator() (@JsonProperty("cores") cores: Double,
                                    @JsonProperty("instances") instances: Double,
                                    @JsonProperty("memory") memory: String,
                                    @JsonProperty("labels") labels: Labels)

case class Labels @JsonCreator() (@JsonProperty("version") version: String)


case class RestartPolicy @JsonCreator() (@JsonProperty("type") `type`: Option[String],
                                         @JsonProperty("onSubmissionFailureRetries") onSubmissionFailureRetries: Option[Int],
                                         @JsonProperty("onFailureRetries") onFailureRetries: Option[Int],
                                         @JsonProperty("onSubmissionFailureRetryInterval") onSubmissionFailureRetryInterval: Option[Int],
                                         @JsonProperty("onFailureRetryInterval") onFailureRetryInterval: Option[Int]){
  require(RestartPolicyType.contains(`type`.get))
}


case class Status @JsonCreator() (@JsonProperty("applicationState") applicationState: Option[ApplicationState],
                                  @JsonProperty("submissionAttempts") submissionAttempts: Int,
                                  @JsonProperty("lastSubmissionAttemptTime") lastSubmissionAttemptTime: String,
                                  @JsonProperty("terminationTime") terminationTime: Option[String],
                                  @JsonProperty("sparkApplicationId") sparkApplicationId: Option[String],
                                  @JsonProperty("executionAttempts") executionAttempts: Option[Int],
                                  @JsonProperty("driverInfo") driverInfo: DriverInfo)

case class SparkApplicationSpec @JsonCreator() (@JsonProperty("type") `type`: String,
                                                @JsonProperty("sparkVersion") sparkVersion: String,
                                                @JsonProperty("mode") mode: Option[String],
                                                @JsonProperty("image") image: Option[String],
                                                @JsonProperty("initContainerImage") initContainerImage: Option[String],
                                                @JsonProperty("imagePullPolicy") imagePullPolicy: Option[String],
                                                @JsonProperty("imagePullSecrets") imagePullSecrets: Option[List[String]],
                                                @JsonProperty("mainClass") mainClass: Option[String],
                                                @JsonProperty("mainApplicationFile") mainApplicationFile: String,
                                                @JsonProperty("arguments") arguments: Option[List[String]],
                                                @JsonProperty("sparkConf") sparkConf: Option[Map[String,String]],
                                                @JsonProperty("hadoopConf") hadoopConf: Option[Map[String,String]],
                                                @JsonProperty("sparkConfigMap") sparkConfigMap: Option[String],
                                                @JsonProperty("hadoopConfigMap") hadoopConfigMap: Option[String],
                                                @JsonProperty("volumes") volumes: Option[Volume],
                                                @JsonProperty("driver") driver: DriverSpec,
                                                @JsonProperty("executor") executor: ExecutorSpec,
                                                @JsonProperty("deps") deps: Dependencies,
                                                @JsonProperty("restartPolicy") restartPolicy: Option[RestartPolicy],
                                                @JsonProperty("nodeSelector") nodeSelector: Option[Map[String,String]],
                                                @JsonProperty("failureRetries") failureRetries: Option[Int],
                                                @JsonProperty("retryInterval") retryInterval: Option[Int],
                                                @JsonProperty("pythonVersion") pythonVersion: Option[String],
                                                @JsonProperty("memoryOverheadFactor") memoryOverheadFactor: Option[String],
                                                @JsonProperty("monitoring") monitoring: Option[MonitoringSpec]) {
  require(SparkApplicationType.contains(`type`))
  require(DeployMode.contains(mode.get))
}

case class ApplicationState @JsonCreator() (@JsonProperty("state") state: String,
                                            @JsonProperty("errorMessage") errorMessage: Option[String]) {
  require(ApplicationStateType.contains(state))
}

case class Dependencies @JsonCreator() (@JsonProperty("jars") jars: Option[List[String]],
                                        @JsonProperty("files") files: Option[List[String]],
                                        @JsonProperty("pyFiles") pyFiles: Option[List[String]],
                                        @JsonProperty("jarsDownloadDir") jarsDownloadDir: Option[String],
                                        @JsonProperty("filesDownloadDir") filesDownloadDir: Option[String],
                                        @JsonProperty("downloadTimeout") downloadTimeout: Option[Int],
                                        @JsonProperty("maxSimultaneousDownloads") maxSimultaneousDownloads: Option[Int])

case class DriverSpec @JsonCreator()  (@JsonProperty("cores") cores: Option[Float],
                                       @JsonProperty("coreLimit") coreLimit: Option[String],
                                       @JsonProperty("memory") memory: Option[String],
                                       @JsonProperty("memoryOverhead") memoryOverhead: Option[String],
                                       @JsonProperty("image") image: Option[String],
                                       @JsonProperty("configMaps") configMaps: Option[List[NamePath]],
                                       @JsonProperty("secrets") secrets: Option[List[SecretInfo]],
                                       @JsonProperty("envVars") envVars: Option[Map[String,String]],
                                       @JsonProperty("envSecretKeyRefs") envSecretKeyRefs: Option[Map[String,Any]],
                                       @JsonProperty("labels") labels: Option[Map[String,String]],
                                       @JsonProperty("annotations") annotations: Option[Map[String,String]],
                                       @JsonProperty("volumeMounts") volumeMounts: Option[VolumeMount],
                                       @JsonProperty("affinity") affinity: Option[Affinity],
                                       @JsonProperty("tolerations") tolerations: Option[List[Toleration]],
                                       @JsonProperty("securityContext") securityContext: Option[PodSecurityContext],
                                       @JsonProperty("podName") podName: Option[String],
                                       @JsonProperty("serviceAccount") serviceAccount: Option[String],
                                       @JsonProperty("javaOptions") javaOptions: Option[String])

case class ExecutorSpec @JsonCreator()  (@JsonProperty("cores") cores: Option[Float],
                                         @JsonProperty("coreLimit") coreLimit: Option[String],
                                         @JsonProperty("memory") memory: Option[String],
                                         @JsonProperty("memoryOverhead") memoryOverhead: Option[String],
                                         @JsonProperty("image") image: Option[String],
                                         @JsonProperty("configMaps") configMaps: Option[List[NamePath]],
                                         @JsonProperty("secrets") secrets: Option[List[SecretInfo]],
                                         @JsonProperty("envVars") envVars: Option[Map[String,String]],
                                         @JsonProperty("envSecretKeyRefs") envSecretKeyRefs: Option[Map[String,String]],
                                         @JsonProperty("labels") labels: Option[Map[String,String]],
                                         @JsonProperty("annotations") annotations: Option[Map[String,String]],
                                         @JsonProperty("volumeMounts") volumeMounts: Option[VolumeMount],
                                         @JsonProperty("affinity") affinity: Option[Affinity],
                                         @JsonProperty("tolerations") tolerations: Option[List[Toleration]],
                                         @JsonProperty("securityContext") securityContext: Option[PodSecurityContext],
                                         @JsonProperty("podName") podName: Option[String],
                                         @JsonProperty("serviceAccount") serviceAccount: Option[String],
                                         @JsonProperty("instances") instances: Option[Int],
                                         @JsonProperty("coreRequest") coreRequest: Option[String],
                                         @JsonProperty("javaOptions") javaOptions: Option[String])

case class NamePath @JsonCreator() (@JsonProperty("name") name: String,
                                    @JsonProperty("path") path: String)

case class DriverInfo @JsonCreator() (@JsonProperty("webUIServiceName") webUIServiceName: Option[String],
                                      @JsonProperty("webUIPort") webUIPort: Option[Int],
                                      @JsonProperty("webUIAddress") webUIAddress: Option[String],
                                      @JsonProperty("webUIIngressName") webUIIngressName: Option[String],
                                      @JsonProperty("webUIIngressAddress") webUIIngressAddress: Option[String],
                                      @JsonProperty("podName") podName: Option[String])

case class SecretInfo @JsonCreator() (@JsonProperty("name") name: String,
                                      @JsonProperty("path") path: String,
                                      @JsonProperty("type") `type`: String){
  require(SecretType.contains(`type`))
}

case class NameKey @JsonCreator() (@JsonProperty("name") name: String,
                                   @JsonProperty("key") key: String)


case class MonitoringSpec @JsonCreator() (@JsonProperty("exposeDriverMetrics") exposeDriverMetrics: Boolean,
                                      @JsonProperty("exposeExecutorMetrics") exposeExecutorMetrics: Boolean,
                                      @JsonProperty("metricsProperties") metricsProperties: Option[String],
                                      @JsonProperty("prometheus") prometheus: Option[PrometheusSpec])

case class PrometheusSpec @JsonCreator() (@JsonProperty("jmxExporterJar") jmxExporterJar: String,
                                          @JsonProperty("port") port: Int,
                                          @JsonProperty("configFile") configFile: Option[String],
                                          @JsonProperty("configuration") configuration: Option[String])