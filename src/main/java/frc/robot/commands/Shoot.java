package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Shooter;

public class Shoot extends InstantCommand {
    private final Shooter shooter;
    public Shoot(Shooter subsystem) {
        shooter = subsystem;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.shoot(targetDistance); //TODO: add distance from limelight to goal from future vision subsytem
    }

    @Override
    public void end(boolean interrupted) {
        shooter.halt();
    }
}
