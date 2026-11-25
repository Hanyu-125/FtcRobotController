package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class DCMotors {
    private DcMotor feed;
    private DcMotorEx flywheelMotor;
    private double highVelocity=1950;
    private double lowVelocity=1000;
    double curTargetVelocity= highVelocity;

    public void init(HardwareMap hwMap){
        feed=hwMap.get(DcMotor.class,"escoba");
        feed.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor=hwMap.get(DcMotorEx.class, "lanzador");
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        PIDFCoefficients pidfCoefficients=new PIDFCoefficients(15.0,0,0,15.0);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);

    }

    public void set_FlywheelMotor(boolean y){
        if(y){
            if(curTargetVelocity==lowVelocity){
                curTargetVelocity=highVelocity;
            }else{curTargetVelocity=lowVelocity;}
        }
        flywheelMotor.setVelocity(curTargetVelocity);
    }

    public void speedFeed(double speed){
        feed.setPower(speed);
    }
}
