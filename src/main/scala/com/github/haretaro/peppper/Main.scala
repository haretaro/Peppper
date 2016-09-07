package com.github.haretaro.peppper

import com.github.haretaro.peppper.Util._
import com.google.code.chatterbotapi.{ChatterBotFactory, ChatterBotType}
import jp.vstone.RobotLib.{CRobotMem, CSotaMotion}
import jp.vstone.sotatalk.{MotionAsSotaWish, SpeechRecog}

import scala.annotation.tailrec

/**
  * @author Haretaro
  */
object Main extends App{
  val mem = new CRobotMem()
  val motion = new CSotaMotion(mem)
  val sotaWish = new MotionAsSotaWish(motion)
  val recog = new SpeechRecog(motion)

  if(mem.Connect()){
    motion.InitRobot_Sota()
    initServo(motion)

    try {
      sotaWish.Say("こんにちは")

      val bot = (new ChatterBotFactory).create(ChatterBotType.CLEVERBOT)
      val botSession = bot.createSession()
      val recogInterval = 10000

      @tailrec
      def recogLoop(recogResult: Option[SpeechRecog.RecogResult]): Unit = recogResult match {
        case Some(result) if result.isRecognized =>{
          val recogResult = Option(result.CheckBest(Array("さよなら"),false))
          recogResult match{
            case Some(speech) if speech.contains("さよなら") => sotaWish.Say("バイバイ")
            case _ => {
              val answer = botSession.think(result.getBasicResult())
              println(result.getBasicResult(),"->",answer)
              sotaWish.Say(answer)
              recogLoop(Option(recog.getRecognition(recogInterval)))
            }
          }
        }
        case _ => recogLoop(Option(recog.getRecognition(recogInterval)))
      }

      recogLoop(Option(recog.getRecognition(recogInterval)))

    }catch{
      case e:Exception => e.printStackTrace()
    }

    System.exit(0)
  }
}
