package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimberBrake extends CommandBase {
    
    private final Climber climber;

    public ClimberBrake(Climber subsystem) {
        climber = subsystem;
        addRequirements(subsystem);
    }

    // Sets climber motors to run at full speed.
    @Override
    public void execute() {
        climber.brake();
        System.out.println("HAHAHAHAHAHAHAHA BRAKE");
    }

    @Override
    public boolean isFinished() {
        System.out.println("Climber is finished.");
        return true;
    }

    
    
}
