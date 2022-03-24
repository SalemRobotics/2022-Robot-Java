package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeIn extends CommandBase{
    private final Intake intake;

    public IntakeIn(Intake subsystem) {
        intake = subsystem;

        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.extend();
        intake.collect(1.0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        intake.retract();
        intake.halt();
    }
}
