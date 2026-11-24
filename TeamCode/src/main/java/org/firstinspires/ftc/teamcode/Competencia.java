package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

@TeleOp
public class Competencia extends OpMode {
    double endGame;
    boolean isEndGame;
    MecanumDrive drive=new MecanumDrive();
    double forward,strafe,rotate;

    @Override
    public void init() {
        drive.init(hardwareMap);
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
    }
}
