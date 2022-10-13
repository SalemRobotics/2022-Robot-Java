package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimberUnspool extends CommandBase
{
    final Climber climber;

    public ClimberUnspool(Climber subsystem)
    {
        climber = subsystem;
        addRequirements(climber);
    }

    @Override
    public void execute() 
    {
        climber.climb(-0.5);
        climber.solenoid.set(Value.kReverse);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
    @Override
    public void end(boolean interrupted) {
        climber.climb(0.0);
        climber.solenoid.set(Value.kForward);
    }
}

