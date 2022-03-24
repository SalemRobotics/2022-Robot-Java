package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;

import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.DrivetrainConstants.DriveTrainPIDConstants;

/** Drivetrain uses 4 TalonFX motors, using differntial drive or "tank" drive. */
public class Drivetrain extends SubsystemBase {

  WPI_TalonFX leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor;
  TalonFXConfiguration leftConfig, rightConfig;
  PigeonIMU gyro;
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

    gyro = new PigeonIMU(1);

    // Create motor configs
    leftConfig = new TalonFXConfiguration();
    rightConfig = new TalonFXConfiguration();

    // set remote sensor source
    leftConfig.remoteFilter0.remoteSensorSource = RemoteSensorSource.GadgeteerPigeon_Yaw;
    leftConfig.remoteFilter0.remoteSensorDeviceID = gyro.getDeviceID();

    // set PID sensor
    leftConfig.auxiliaryPID.selectedFeedbackSensor = FeedbackDevice.RemoteSensor0;
    leftConfig.auxiliaryPID.selectedFeedbackCoefficient = 
    DriveTrainPIDConstants.turnTravelUnitsPerRotation / DriveTrainPIDConstants.pigeonUnitsPerRotation;
    
    // PID config
    leftConfig.slot1.kP = DriveTrainPIDConstants.P;
    leftConfig.slot1.kI = DriveTrainPIDConstants.I;
    leftConfig.slot1.kD = DriveTrainPIDConstants.D;
    leftConfig.slot1.kF = DriveTrainPIDConstants.F;
    leftConfig.slot1.integralZone = DriveTrainPIDConstants.Iz;
    leftConfig.slot1.closedLoopPeakOutput = DriveTrainPIDConstants.peak;
    leftConfig.slot1.allowableClosedloopError = 0;

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

  /** Zeroes the gyro's heading */
  public void zeroHeading() {
    gyro.setFusedHeading(0.0);
  }  

  public void alignToTarget(double errorAngle) {
    zeroHeading();
    leftFrontMotor.selectProfileSlot(1, 1);
    leftFrontMotor.set(TalonFXControlMode.PercentOutput, 0.0, DemandType.AuxPID, errorAngle);
    rightFrontMotor.follow(leftFrontMotor, FollowerType.AuxOutput1);
    rightFrontMotor.setInverted(TalonFXInvertType.OpposeMaster);
  }
}
