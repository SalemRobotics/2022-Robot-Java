package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

public class CheckSpeed extends CommandBase implements BooleanSupplier {
    private final Shooter shooter;
    public CheckSpeed(Shooter subsystem) {
        shooter = subsystem;
        addRequirements(shooter);
    }

    @Override
    public void execute() {}

    @Override
    public boolean getAsBoolean() {
        return shooter.dumbCheckSpeed(ShooterConstants.flywheelSpeed - 300);
    }
}
