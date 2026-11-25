package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.DCMotors;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.ServoMotors;

@TeleOp
public class Competencia extends OpMode {
    double endGame;
    boolean isEndGame;
    DCMotors motores=new DCMotors();
    ServoMotors servos = new ServoMotors();
    MecanumDrive drive=new MecanumDrive();
    double forward,strafe,rotate;

    @Override
    public void init() {
        drive.init(hardwareMap);
        servos.init(hardwareMap);
        motores.init(hardwareMap);
        telemetry.addLine("Init complete");


    }

    public void start(){
        endGame=getRuntime()+90;
    }

    @Override
    public void loop() {
        //timer
        if(endGame>=getRuntime()&&!isEndGame){
            gamepad1.rumbleBlips(3);
            isEndGame=true;
        }

        forward =gamepad1.left_stick_y;
        strafe=gamepad1.left_stick_x;
        rotate=gamepad1.right_stick_x;

        drive.driveFieldRelative(forward,strafe,rotate);
        motores.set_FlywheelMotor(gamepad2.yWasPressed());


        if (gamepad2.left_bumper){
            servos.setServo_Pos1(0);
        }else{servos.setServo_Pos1(1.0);}

        if(gamepad2.rightBumperWasPressed()) {
            motores.speedFeed(0.5);
        }else{motores.speedFeed(0);}
    }
}
