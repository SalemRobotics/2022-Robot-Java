package frc.robot.commands;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants.DrivetrainConstants.DrivetrainAutoConstants;
import frc.robot.subsystems.Drivetrain;

public class DriveAuton {

    DifferentialDriveVoltageConstraint voltageConstraint;
    TrajectoryConfig config;
    Trajectory trajectory;
    RamseteCommand ramseteCommand;
    SimpleMotorFeedforward feedforward;

    private final DifferentialDriveKinematics driveKinematics = 
    new DifferentialDriveKinematics(DrivetrainAutoConstants.trackWidthMeters);

    public Command getCommand(Drivetrain drive) {


        feedforward = new SimpleMotorFeedforward(
            // DrivetrainAutoConstants.ksVolts, 
            // DrivetrainAutoConstants.kvVoltSecondsPerMeter, 
            // DrivetrainAutoConstants.kaVoltSecondsSquaredPerMeter
            0.0, 0.0, 0.0
        );

        voltageConstraint = new DifferentialDriveVoltageConstraint(
            feedforward,
            driveKinematics,
            10
        );
        
        config = new TrajectoryConfig(
            DrivetrainAutoConstants.maxSpeedMetersPerSecond, 
            DrivetrainAutoConstants.maxAccelerationMetersPerSecondSq
        )
        .setKinematics(driveKinematics)
        .addConstraint(voltageConstraint)
        .setReversed(false);
    
        trajectory = TrajectoryGenerator.generateTrajectory(
            List.of(new Pose2d(0, 0, new Rotation2d()), new Pose2d(2, 0, new Rotation2d())),
            config
        );

        ramseteCommand = new RamseteCommand(
            trajectory, 
            drive::getPose, 
            new RamseteController(DrivetrainAutoConstants.ramseteB, DrivetrainAutoConstants.ramseteZeta), 
            feedforward,
            driveKinematics, 
            drive::getWheelSpeeds, 
            new PIDController(DrivetrainAutoConstants.kP, DrivetrainAutoConstants.kI, DrivetrainAutoConstants.kD), 
            new PIDController(DrivetrainAutoConstants.kP, DrivetrainAutoConstants.kI, DrivetrainAutoConstants.kD), 
            drive::tankDriveVolts, 
            drive
        );

        drive.resetOdometry(trajectory.getInitialPose());

        return ramseteCommand.andThen(() -> drive.tankDriveVolts(0, 0));
    }
}
