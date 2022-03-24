package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeRetract extends CommandBase {
    private final Intake intake;
    public IntakeRetract(Intake subsystem) {
        intake = subsystem;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.retract();
    }
}
