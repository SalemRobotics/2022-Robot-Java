package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimberBrakeRelease extends CommandBase {
    
    private final Climber climber;

    public ClimberBrakeRelease(Climber subsystem) {
        climber = subsystem;
        addRequirements(subsystem);
    }

    // Sets climber motors to run at full speed.
    @Override
    public void execute() {
        climber.releaseBrake();
        System.out.println("HAHAHAHAHAHAHAHA NO BRAKE");
    }

    @Override
    public boolean isFinished() {
        System.out.println("Climber is always finished.");
        return true;
    }

    
    
}

