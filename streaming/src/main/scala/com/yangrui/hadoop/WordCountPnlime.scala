package com.yangrui.hadoop

import org.apache.spark.SparkConf
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


object WordCountPnlime {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[1]")
    conf.setAppName("wordCount")
    conf.set("spark.testing.memory", "471859200")
    val ssc = new StreamingContext(conf, Seconds(3))
    val dStream = ssc.socketTextStream("192.168.1.100", 9999)
    val words = dStream.flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_+_)
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()



     FlumeUtils.
  }

}
