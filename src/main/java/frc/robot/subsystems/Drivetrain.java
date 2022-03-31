package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;

import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.DrivetrainConstants.DriveTrainPIDConstants;
import frc.robot.Constants.DrivetrainConstants.DrivetrainAutoConstants;

/** Drivetrain uses 4 TalonFX motors, using differntial drive or "tank" drive. */
public class Drivetrain extends SubsystemBase {

  public WPI_TalonFX leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor;
  TalonFXConfiguration leftConfig, rightConfig;
  PigeonIMU gyro;
  DifferentialDrive difDrive;
  public DifferentialDriveOdometry odometry;

  public Drivetrain() {

    //left motors
    leftFrontMotor = new WPI_TalonFX(DrivetrainConstants.kLeftFrontPort);
    leftFrontMotor.setNeutralMode(NeutralMode.Coast);
    leftFrontMotor.setSensorPhase(false);
    leftRearMotor = new WPI_TalonFX(DrivetrainConstants.kLeftRearPort);
    leftRearMotor.setNeutralMode(NeutralMode.Coast);
    leftRearMotor.follow(leftFrontMotor);
    leftRearMotor.setInverted(InvertType.FollowMaster);
    leftFrontMotor.setInverted(TalonFXInvertType.CounterClockwise);

    // leftMotors = new MotorControllerGroup(
    //   leftFrontMotor, 
    //   leftRearMotor
    // );

    //right motors
    rightFrontMotor = new WPI_TalonFX(DrivetrainConstants.kRightFrontPort);
    rightFrontMotor.setNeutralMode(NeutralMode.Coast);
    rightRearMotor = new WPI_TalonFX(DrivetrainConstants.kRightRearPort);
    rightRearMotor.setNeutralMode(NeutralMode.Coast);
    rightRearMotor.follow(rightFrontMotor);
    rightRearMotor.setInverted(InvertType.FollowMaster);
    // rightMotors = new MotorControllerGroup(
    //   rightFrontMotor,
    //   rightRearMotor
    // );
    rightFrontMotor.setInverted(TalonFXInvertType.Clockwise);

    // Create motor configs
    leftConfig = new TalonFXConfiguration();
    rightConfig = new TalonFXConfiguration();
    gyro = new PigeonIMU(1);

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

    // Set differential drive
    difDrive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);

    // Create gyro and odometry
    odometry = new DifferentialDriveOdometry(getRotation2d());

    resetEncoders();
  }

  @Override
  public void periodic() {
      odometry.update(
        getRotation2d(), 
        leftFrontMotor.getSelectedSensorPosition() * DrivetrainAutoConstants.encoderMetersFromPulses, 
        rightFrontMotor.getSelectedSensorPosition() * DrivetrainAutoConstants.encoderMetersFromPulses
      );
  }

  /**
   * Sets motors to use arcade drive
   * @param fwd Forward axis of drivetrain (Y axis)
   * @param rot Axis of rotation of drivetrain (X axis)
   */
  public void arcadeDrive(double fwd, double rot) {
    difDrive.arcadeDrive(-fwd, rot, true);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftFrontMotor.set(leftVolts);
    rightFrontMotor.set(-rightVolts);
    difDrive.feed();
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    DifferentialDriveWheelSpeeds speeds = new DifferentialDriveWheelSpeeds(
      leftFrontMotor.getSelectedSensorVelocity() * DrivetrainAutoConstants.metersPerSecondFromPulses, 
      rightFrontMotor.getSelectedSensorVelocity() * DrivetrainAutoConstants.metersPerSecondFromPulses
      );
    return speeds;
  }

  public Rotation2d getRotation2d() {
    double[] ypr = new double[3];
    gyro.getYawPitchRoll(ypr);
    return Rotation2d.fromDegrees(ypr[0] * -1);
  }

  public void resetEncoders() {
    leftFrontMotor.setSelectedSensorPosition(0.0);
    rightFrontMotor.setSelectedSensorPosition(0.0);
  }

  public double getAverageEncoderDistance() {
    return (
      leftFrontMotor.getSelectedSensorPosition() * DrivetrainAutoConstants.encoderMetersFromPulses +
      rightFrontMotor.getSelectedSensorPosition() * DrivetrainAutoConstants.encoderMetersFromPulses
    ) / 2.0;
  }

  public void setMaxOutput(double maxOutput) {
    difDrive.setMaxOutput(maxOutput);
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, getRotation2d());
  }

  public void getHeading() {
    getRotation2d().getDegrees();
  }

  /** Zeroes the gyro's heading */
  public void zeroHeading() {
    gyro.setFusedHeading(0.0);
  }  

  public double getTurnRate() {
    double[] xyz = new double[3];
    gyro.getRawGyro(xyz);
    return xyz[1];
  }

  public void alignToTarget(double errorAngle) {
    zeroHeading();
    
    leftFrontMotor.selectProfileSlot(1, 1);
    leftRearMotor.follow(leftFrontMotor);
    leftFrontMotor.set(TalonFXControlMode.PercentOutput, 0.0, DemandType.AuxPID, errorAngle);

    rightFrontMotor.follow(leftFrontMotor, FollowerType.AuxOutput1);
    rightFrontMotor.setInverted(TalonFXInvertType.OpposeMaster);
    rightRearMotor.follow(rightFrontMotor);
  }
}
