package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {

  WPI_TalonFX leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor;
  public static DifferentialDrive difDrive;

  public Drivetrain() {
    leftFrontMotor = new WPI_TalonFX(DrivetrainConstants.kLeftFrontPort);
    leftRearMotor = new WPI_TalonFX(DrivetrainConstants.kLeftRearPort);
    leftRearMotor.follow(leftFrontMotor);

    rightFrontMotor = new WPI_TalonFX(DrivetrainConstants.kRightFrontPort);
    rightRearMotor = new WPI_TalonFX(DrivetrainConstants.kRightRearPort);
    rightRearMotor.follow(rightFrontMotor);

    difDrive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
  }

  public void arcadeDrive(double fwd, double rot) {
    difDrive.arcadeDrive(fwd, rot);
  }
  
}
