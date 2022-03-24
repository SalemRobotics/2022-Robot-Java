package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.SpeedConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

public class AlignDrivetrain extends CommandBase {
    private final Drivetrain drive;
    private final Vision vision;
    public AlignDrivetrain(Drivetrain driveSubsystem, Vision visionSubsystem) {
        drive = driveSubsystem;
        vision = visionSubsystem;
        addRequirements(drive, vision);
    }

    @Override
    public void execute() {
        drive.alignToTarget(vision.getTargetYaw());
    }

    @Override
    public boolean isFinished() {
        if (vision.getTargetYaw() <= SpeedConstants.alignmentAcceptableError) return true;
        return false;
    }

    @Override
    public void end(boolean interrupted) {}
}
