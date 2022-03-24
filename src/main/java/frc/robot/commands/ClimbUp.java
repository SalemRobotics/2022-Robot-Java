package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimbUp extends CommandBase {
    
    private final Climber climber;

    public ClimbUp(Climber subsystem) {
        climber = subsystem;
        addRequirements(subsystem);
    }

    // Sets climber motors to run at full speed.
    @Override
    public void execute() {
        climber.climb(1.0, false);
        System.out.println("Climber is moving up!");
    }

    @Override
    public boolean isFinished() {
        System.out.println("Climber is finished.");
        return false;
    }

    // Halts motors and toggles brake when command ends.
    @Override
    public void end(boolean interrupted) {
        System.out.println("All your climber belong to us. Climber up ends.");
        climber.halt();
    //    climber.brake();
    }
}
