package com.github.haretaro.peppper

import jp.vstone.RobotLib.{CRobotPose, CRobotUtil, CSotaMotion}

/**
  * @author Haretaro
  */
object Util {
  /**
    * サーボの初期化
    * @param motion
    */
  def initServo(motion: CSotaMotion) = {
    motion.ServoOn()
    val pose = new CRobotPose()
    pose.SetPose(Array[Byte](1, 2, 3, 4, 5, 6, 7, 8), Array[Short](0, -900, 0, 900, 0, 0, 0, 0))
    pose.SetTorque(Array[Byte](1, 2, 3, 4, 5, 6, 7, 8), Array[Short](100, 100, 100, 100, 100, 100, 100, 100))
    pose.SetLed(Array[Byte](0, 1, 2, 8, 9, 10, 11, 12, 13), Array[Short](0, -255, 0, 180, 80, 0, 180, 80, 0))
    motion.play(pose, 1500)
    CRobotUtil.wait(1500)
  }

  implicit def toJavaByteArray(xs: Array[Byte]): Array[java.lang.Byte] = xs.map(_.asInstanceOf[java.lang.Byte])
  implicit def toJavaShortArray(xs: Array[Short]): Array[java.lang.Short] = xs.map(_.asInstanceOf[java.lang.Short])
}
