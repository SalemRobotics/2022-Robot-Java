package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Climber;

public class ClimbDown extends InstantCommand{
    private final Climber climber;
    
    public ClimbDown(Climber subsystem) {
        climber = subsystem;
        addRequirements(climber);
    }

    // Runs motors in reverse at full speed.
    @Override
    public void execute() {
        climber.climb(-1.0);
    }

    // Halts motors and toggles brake when command ends.
    @Override
    public void end(boolean interrupted) {
        climber.halt();
        climber.brake();
    }
}
