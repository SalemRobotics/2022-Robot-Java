package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants.DrivetrainConstants;

/** Drivetrain uses 4 TalonFX motors, using differntial drive or "tank" drive. */
public class Drivetrain extends SubsystemBase {

  WPI_TalonFX leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor;
  MotorControllerGroup leftMotors, rightMotors;
  public static DifferentialDrive difDrive;

  public Drivetrain() {
    leftFrontMotor = new WPI_TalonFX(DrivetrainConstants.kLeftFrontPort);
    leftRearMotor = new WPI_TalonFX(DrivetrainConstants.kLeftRearPort);
    leftMotors = new MotorControllerGroup(
      leftFrontMotor, 
      leftRearMotor
    );

    rightFrontMotor = new WPI_TalonFX(DrivetrainConstants.kRightFrontPort);
    rightRearMotor = new WPI_TalonFX(DrivetrainConstants.kRightRearPort);
    rightMotors = new MotorControllerGroup(
      rightFrontMotor,
      rightRearMotor
    );
    rightMotors.setInverted(true);

    difDrive = new DifferentialDrive(leftMotors, rightMotors);
  }

  /**
   * Sets motors to use arcade drive
   * @param fwd Forward axis of drivetrain (Y axis)
   * @param rot Axis of rotation of drivetrain (X axis)
   */
  public void arcadeDrive(double fwd, double rot) {
    difDrive.arcadeDrive(-fwd, rot, true);
  }
  
}
