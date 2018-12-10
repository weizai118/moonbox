package moonbox.client.javaAPI

import java.lang._
import java.net.InetSocketAddress
import java.util
import java.util.{Map => JMap}

import moonbox.client.MoonboxClient
import moonbox.client.entity.{ConnectionState, MoonboxRowSet}

import scala.collection.JavaConverters._

class JavaMoonboxClient private (moonboxClient: MoonboxClient) {

  def this() = this(MoonboxClient.builder().build())
  def this(key: String, value: String) = this(MoonboxClient.builder().setConf(key, value).build())
  def this(kv: JMap[String, String]) = this(MoonboxClient.builder().setConf(kv.asScala).build())

  /** constructors */
  def newClient: JavaMoonboxClient = new JavaMoonboxClient(moonboxClient.newClient)

  /** connection related */
  def version: String = moonboxClient.version
  def isConnected: Boolean = moonboxClient.isConnected
  def close(): Unit = moonboxClient.close()
  def getReadTimeout: Int = moonboxClient.getReadTimeout
  def setReadTimeout(milliseconds: Int): Unit = moonboxClient.setReadTimeout(milliseconds)
  def getFetchSize: Int = moonboxClient.getFetchSize
  def setFetchSize(size: Int): Unit = moonboxClient.setFetchSize(size)
  def getServers: util.List[InetSocketAddress] = moonboxClient.getServers.asJava
  def getConf(key: String): String = moonboxClient.getConf(key).orNull
  def getAllConf: JMap[String, String] = moonboxClient.getAllConf

  /** userSys related */
  def userInfo: Object = moonboxClient.userInfo

  /** metadata related */
  /* list */
  def listDatabases: util.List[String] = moonboxClient.listDatabases.asJava
  def listTables(database: String): util.List[String] = moonboxClient.listTables(database).asJava
  def listViews(database: String): util.List[String] = moonboxClient.listViews(database).asJava
  def listFunctions(database: String): util.List[String] = moonboxClient.listFunctions(database).asJava
  def listVariables(username: String): util.List[String] = moonboxClient.listVariables(username).asJava
  /* alter */
  /* exists */
  /* create */
  /* drop */

  /** interactive query related */
  def getConnectionState: ConnectionState = moonboxClient.getConnectionState
  def getCurrentDatabase: String = moonboxClient.getCurrentDatabase
  def setCurrentDatabase(databaseName: String): Unit = moonboxClient.setCurrentDatabase(databaseName)
  def interactiveQuery(interactiveSql: util.List[String]): MoonboxRowSet = moonboxClient.interactiveQuery(interactiveSql.asScala)

  /** batch query related */
  def submitJob(jobSql: util.List[String], config: JMap[String, String]): String = moonboxClient.submitJob(jobSql.asScala, config)
  /* return jobId */
  def runningJobs: util.List[String] = moonboxClient.runningJobs.asJava
  def waitingJobs: util.List[String] = moonboxClient.waitingJobs.asJava
  def failedJobs: util.List[String] = moonboxClient.failedJobs.asJava
  def jobHistory: util.List[Object] = moonboxClient.jobHistory.asJava

  /** timer related */
  /** privilege related */

}