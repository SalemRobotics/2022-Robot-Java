package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.SpeedConstants;

/**
 * Implements shooter subsystem with 2 TalonFXs.
 * This class contains code that is able to calculate a shooting speed from 
 * a given distance. 
 */
public class Shooter extends SubsystemBase {

    WPI_TalonFX flywheelA, flywheelB;
    List<Double> flywheelShots;


    public Shooter() {
        flywheelA = new WPI_TalonFX(ShooterConstants.flywheelAPort);
        flywheelB = new WPI_TalonFX(ShooterConstants.flywheelBPort);
        flywheelB.follow(flywheelA);
        flywheelB.setInverted(InvertType.OpposeMaster);

        flywheelShots = new ArrayList<Double>();
    }

    public void manualShoot() {
        flywheelA.set(TalonFXControlMode.PercentOutput, 0.50);
    }

    public boolean dumbCheckSpeed(int targetSpeed) {
        if (flywheelA.getSelectedSensorVelocity() >= targetSpeed) return true;
        return false;
    }

    /** 
     * Sets a PID reference using a calculated RPM given targetDistance.
     * Saves each shot speed in a list for use in calculating shot average.
     * 
     * This function allows the actual shooting of the ball from the robot to the target.
     * @param targetDistance Distance from limelight to limelight target in feet. 
     */
    public void shoot(double targetDistance) {
        double flywheelRpm = speedCalc(targetDistance);
        flywheelA.set(TalonFXControlMode.Velocity, rpmToMagic(flywheelRpm));
        flywheelShots.add(flywheelRpm);
    }

    /**
    * Sets a new PID reference based on the average of all previous shots. 
    * This function is used to prepare the flywheel for a shot. 
    */
    public void spinDash() {
        double averageShotRpm = flywheelShots.stream().mapToDouble(Double::doubleValue).sum() / flywheelShots.size();
        flywheelA.set(TalonFXControlMode.Velocity, rpmToMagic(averageShotRpm));
    }

    /**
     * Converts RPM to ticks
     * TODO: Better explanations for these functions
     * @param rpm RPM to convert to ticks
     * @return Number of ticks / 600
     */
    public double rpmToMagic(double rpm) {
        double ticks = rpm * 2048;
        return ticks / 600;
    }

    /**
     * Converts ticks to RPM
     * TODO: Better explanations for these functions
     * @param ticksPHS Ticks to convert to RPM
     * @return RPM * 600
     */
    public double magicToRpm(double ticksPHS) {
        double rpm = ticksPHS / 2048;
        return rpm * 600;
    }

    /**
     * Checks if the flywheel's real speed is within 5% of the target speed.
     * @param targetDistance Distance from limelight to limelight target in feet.
     * @return true if the real speed is within 5% of the target speed.
     */
    public boolean checkSpeed(double targetDistance) {
        double targetSpeed = speedCalc(targetDistance);
        double realSpeed = magicToRpm(flywheelA.getSensorCollection().getIntegratedSensorVelocity());
        double fivePercent = 0.05 * targetSpeed;

        if ((realSpeed - targetSpeed) <= fivePercent) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the desired RPM of the flywheel based on the distance from limelight to target. 
     * @param targetDistance Distance from limelight to limelight target in feet.
     * @return Calculated speed in RPM.
     */
    public double speedCalc(double targetDistance) {
        double heightChange = SpeedConstants.targetHeight - SpeedConstants.shootHeight;
        double realDistance = targetDistance + SpeedConstants.goalOffset + SpeedConstants.robotOffsetX;

        double targetVelocity = (2 * heightChange * Math.cos(Math.toRadians(SpeedConstants.shootAngle))) / 
        ((realDistance * Math.pow(SpeedConstants.gravity, 2)) + 
        Math.sin(Math.toRadians(SpeedConstants.shootAngle)));

        double flywheelRPM = (targetVelocity * 12 * 60) / SpeedConstants.shooterWheelCircumference;

        return flywheelRPM;
    }

    public void halt() {
        flywheelA.set(TalonFXControlMode.PercentOutput, 0.0);
    }
}
