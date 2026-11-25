package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoMotors {
    private Servo servoPos1;
    private Servo servoPos2;
    // private CRServo servoRot;

    public void init(HardwareMap hwMap){
        servoPos1 = hwMap.get(Servo.class,"servo1");
        servoPos2 = hwMap.get(Servo.class,"servo2");
        // servoRot = hwMap.get(CRServo.class,"servocont");

    }

    public void setServo_Pos1(double angle){
        servoPos1.setPosition(angle);
    }
    public void setServo_Pos2(double angle){
        servoPos2.setPosition(angle);
    }
    /*
    public void setServo_Rot(double Power) {
        servoRot.setPower(Power);
    }*/
}
