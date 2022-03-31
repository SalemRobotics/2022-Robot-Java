package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Shooter;

public class SpinDash extends InstantCommand{
    private final Shooter shooter;
    public SpinDash(Shooter subsystem) {
        shooter = subsystem;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.spinDash();
    }

    @Override
    public void end(boolean interrupted) {
        shooter.halt();
    }
}
