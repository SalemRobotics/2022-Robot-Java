package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
    private final Shooter shooter;
    private final double speed;
    public Shoot(Shooter subsystem, double speed) {
        shooter = subsystem;
        this.speed = speed;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        // shooter.shoot(targetDistance); //TODO: add distance from limelight to goal from future vision subsytem
        shooter.manualShoot(speed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        shooter.halt();
    }
}
