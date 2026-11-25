package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp
public class flywheelCal extends OpMode {
    public DcMotorEx flywheelMotor;
    public double highVelocity=1950;
    public double lowVelocity=1000;
    double curTargetVelocity= highVelocity;
    double F=0;
    double P=0;
    double[] stepSizes={10.0,1.0, 0.1, 0.001,0.0001};
    int stepIndex=1;


    @Override
    public void init() {
        flywheelMotor=hardwareMap.get(DcMotorEx.class, "lanzador");
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        PIDFCoefficients pidfCoefficients=new PIDFCoefficients(P,0,0,F);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);
        telemetry.addLine("Init complete");

    }

    @Override
    public void loop() {
        if(gamepad2.yWasPressed()){
            if(curTargetVelocity==highVelocity){
                curTargetVelocity=lowVelocity;
            }else{curTargetVelocity=highVelocity;}
        }

        if (gamepad2.bWasPressed()){
            stepIndex=(stepIndex+1)%stepSizes.length;
        }

        if(gamepad2.dpadLeftWasPressed()){
            F-=stepSizes[stepIndex];
        }
        if (gamepad2.dpadRightWasPressed()){
            F+=stepSizes[stepIndex];
        }
        if (gamepad2.dpadUpWasPressed()){
            P+=stepSizes[stepIndex];
        }
        if (gamepad2.dpadDownWasPressed()){
            P-=stepSizes[stepIndex];
        }

        //set new PIDF coefficients
        PIDFCoefficients pidfCoefficients=new PIDFCoefficients(P,0,0,F);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);

        flywheelMotor.setVelocity(curTargetVelocity);

        double curVelocity=flywheelMotor.getVelocity();
        double error= curTargetVelocity-curVelocity;

        telemetry.addData("TargetVelocity", curTargetVelocity);
        telemetry.addData("Current Velocity", "%.2f",error);
        telemetry.addData("Error","%.2f",error);
        telemetry.addLine("------------------------------");
        telemetry.addData("Tuning P","%.4f (D-Pad U/D)", P);
        telemetry.addData("Tunning F","%.4f (D-Pad L/R)",F);
        telemetry.addData("Step Sizes","%.4f (B Button)",stepSizes[stepIndex]);
    }
}


