package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class LifterOp extends OpMode {
	DcMotor motorLeft;
	DcMotor motorRight;

	DcMotor elbowLeft;
	DcMotor elbowRight;
	DcMotor wristLeft;
	DcMotor wristRight;

	double ELBOW_ROTATE_SPEED = 0.3;
	double WRIST_ROTATE_SPEED = 0.1;

	@Override
	public void init() {
		elbowLeft = hardwareMap.dcMotor.get("eLeft");
		elbowRight = hardwareMap.dcMotor.get("eRight");

		wristLeft = hardwareMap.dcMotor.get("wLeft");
		wristRight = hardwareMap.dcMotor.get("wRight");

		wristRight.setDirection(DcMotor.Direction.REVERSE);
		elbowRight.setDirection(DcMotor.Direction.REVERSE);

		switchToForward();
	}

	@Override
	public void loop() {
		double left = gamepad1.left_stick_y;
		double right = gamepad1.right_stick_y;
		setWheelSpeeds(left, right);

		if(gamepad1.y){
			switchToReverse();
		}
		else if (gamepad1.b){
			switchToForward();
		}

		if(gamepad1.x){
			elbowLeft.setPower(ELBOW_ROTATE_SPEED);
			elbowRight.setPower(ELBOW_ROTATE_SPEED);
		}
		else if(gamepad1.a){
			elbowLeft.setPower(-ELBOW_ROTATE_SPEED);
			elbowRight.setPower(-ELBOW_ROTATE_SPEED);
		}
		else{
			elbowLeft.setPower(0);
			elbowRight.setPower(0);
		}

		if(gamepad1.left_bumper){
			wristLeft.setPower(WRIST_ROTATE_SPEED);
			wristRight.setPower(WRIST_ROTATE_SPEED);
		}
		else if(gamepad1.right_bumper){
			wristLeft.setPower(-WRIST_ROTATE_SPEED);
			wristRight.setPower(-WRIST_ROTATE_SPEED);
		}
		else{
			wristLeft.setPower(0);
			wristRight.setPower(0);
		}
	}

	private void setWheelSpeeds(double left, double right){
		left = Range.clip(left, -1, 1);
		right = Range.clip(right, -1, 1);
		motorLeft.setPower(left);
		motorRight.setPower(right);
	}

	private void switchToForward(){
		motorLeft = hardwareMap.dcMotor.get("mRight");
		motorRight = hardwareMap.dcMotor.get("mLeft");

		motorRight.setDirection(DcMotor.Direction.REVERSE);
		motorLeft.setDirection(DcMotor.Direction.FORWARD);
	}

	private void switchToReverse(){
		motorLeft = hardwareMap.dcMotor.get("mLeft");
		motorRight = hardwareMap.dcMotor.get("mRight");

		motorRight.setDirection(DcMotor.Direction.REVERSE);
		motorLeft.setDirection(DcMotor.Direction.FORWARD);
	}
}